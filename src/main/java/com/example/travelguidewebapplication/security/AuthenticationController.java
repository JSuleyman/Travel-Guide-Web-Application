package com.example.travelguidewebapplication.security;

import com.example.travelguidewebapplication.exception.NotFoundUser;
import com.example.travelguidewebapplication.exception.NotUniqueUser;
import com.example.travelguidewebapplication.exception.WrongPassword;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserDetailsService userDetailsService;
    private LogoutService logoutService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (NotUniqueUser ex) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(new AuthenticationResponse(null, "This email is already"));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (WrongPassword ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new AuthenticationResponse(null, "Wrong password"));
        } catch (NotFoundUser ex) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(new AuthenticationResponse(null, "No such e-mail address was found"));
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Optional<Token> tokenOptional = tokenRepository.findByToken(token);
        if (tokenOptional.isPresent()) {
            Token token2 = tokenOptional.get();
            if(token2.isExpired() && token2.isRevoked()){

            }else {
                token2.setExpired(true);
                token2.setRevoked(true);
                tokenRepository.save(token2);
                SecurityContextHolder.clearContext();
            }
        }
    }
}
