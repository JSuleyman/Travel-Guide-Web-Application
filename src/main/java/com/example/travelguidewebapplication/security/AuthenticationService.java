package com.example.travelguidewebapplication.security;

import com.example.travelguidewebapplication.exception.*;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.model.UserEmailVerification;
import com.example.travelguidewebapplication.repository.UserEmailVerificationRepository;
import com.example.travelguidewebapplication.repository.UserRepository;
import com.example.travelguidewebapplication.service.impl.EmailSenderServiceImpl;
import com.example.travelguidewebapplication.service.inter.UserEmailVerificationService;
import com.example.travelguidewebapplication.service.inter.UserService;
import com.example.travelguidewebapplication.util.VerificationCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserEmailVerificationService userEmailVerificationService;
    private final EmailSenderServiceImpl emailSenderService;
    private final UserEmailVerificationRepository userEmailVerificationRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        String verificationCode = VerificationCodeGenerator.generateVerificationCode();
        User userVerify = repository.findByEmailForVerify(request.getEmail());

        if (userVerify != null) {
            if (!userVerify.getFirstname().isEmpty()) {
                List<UserEmailVerification> list = userEmailVerificationRepository.listUserById(userVerify.getId());
                for (UserEmailVerification userEmailVerification : list) {
                    userEmailVerification.setHasExpired(true);
                    userEmailVerificationService.save(userEmailVerification);
                }

                var savedUserEmailVerification = UserEmailVerification.builder()
                        .verificationCode(verificationCode)
                        .user(userVerify)
                        .verificationCodeCreatedAt(LocalDateTime.now())
                        .verificationCodeExpirationMinutes(1)
                        .hasExpired(false)
                        .build();
                userEmailVerificationService.save(savedUserEmailVerification);

                emailSenderService.sendEmail(request.getEmail(),
                        "Verification Email code",
                        verificationCode);
                return AuthenticationResponse.builder()
                        .token(null)
                        .message("Code gonderildi")
                        .build();
            }
        }

        if (repository.findAll().stream()
                .filter(User::isVerified)
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(request.getEmail()))) {
            throw new NotUniqueUser();
        }

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .verified(false)
                .build();
        var savedUser = repository.save(user);

        var savedUserEmailVerification = UserEmailVerification.builder()
                .verificationCode(verificationCode)
                .user(savedUser)
                .verificationCodeCreatedAt(LocalDateTime.now())
                .verificationCodeExpirationMinutes(1)
                .hasExpired(false)
                .build();
        userEmailVerificationService.save(savedUserEmailVerification);

        emailSenderService.sendEmail(savedUser.getEmail(),
                "Verification Email code",
                savedUserEmailVerification.getVerificationCode());

        return AuthenticationResponse.builder()
                .token(null)
                .message("Successfully")
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var userByEmail = repository.findByEmail(request.getEmail())
                .orElseThrow(NotFoundUser::new);

        if (!userByEmail.isVerified()) {
            throw new IsNotVerifiredUser();
        }

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
