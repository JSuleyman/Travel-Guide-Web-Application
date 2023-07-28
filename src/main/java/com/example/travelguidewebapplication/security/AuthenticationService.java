package com.example.travelguidewebapplication.security;

import com.example.travelguidewebapplication.error.optimal.TestException;
import com.example.travelguidewebapplication.exception.NotFoundUser;
import com.example.travelguidewebapplication.exception.NotUniqueUser;
import com.example.travelguidewebapplication.exception.PasswordMismatchException;
import com.example.travelguidewebapplication.exception.WrongPassword;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.UserRepository;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.findAll().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(request.getEmail()))) {
            throw new NotUniqueUser();
        }
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(null)
                .message("Successfully")
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var userByEmail = repository.findByEmail(request.getEmail())
                .orElseThrow(NotFoundUser::new);
        if (!passwordEncoder.matches(request.getPassword(), userByEmail.getPassword())) {
            throw new WrongPassword();
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .build();
    }

    public String changeUserPassword(UserChangePasswordRequestDTO userChangePasswordRequestDTO) {
        var userByEmail = userService.getCurrentUser();
        if (!passwordEncoder.matches(userChangePasswordRequestDTO.getOldPassword(), userByEmail.getPassword())) {
            throw new WrongPassword();
        }
        if (!userChangePasswordRequestDTO.getNewPassword().equals(userChangePasswordRequestDTO.getRepeatPassword())) {
            throw new PasswordMismatchException();
        }
        userByEmail.setPassword((passwordEncoder.encode(userChangePasswordRequestDTO.getNewPassword())));
        repository.save(userByEmail);

        return "Parol ugurla deyisdirildi!";
    }

    //Helper Methods

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
