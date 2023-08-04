package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.exception.NotFoundUser;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.model.UserEmailVerification;
import com.example.travelguidewebapplication.repository.UserEmailVerificationRepository;
import com.example.travelguidewebapplication.repository.UserRepository;
import com.example.travelguidewebapplication.service.inter.UserEmailVerificationService;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserEmailVerificationServiceImpl implements UserEmailVerificationService {

    private final UserEmailVerificationRepository userEmailVerificationRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public void save(UserEmailVerification userEmailVerification) {
        userEmailVerificationRepository.save(userEmailVerification);
    }

    @Override
    public String verifyUser(String email, String verificationCode) {
        User user = userRepository.findByEmail(email).stream()
                .findFirst()
                .orElseThrow(NotFoundUser::new);

        UserEmailVerification userEmailVerification = userEmailVerificationRepository.userIsVerified(user.getId());

        LocalDateTime codeCreatedAt = userEmailVerification.getVerificationCodeCreatedAt();
        int codeExpirationMinutes = userEmailVerification.getVerificationCodeExpirationMinutes();

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = codeCreatedAt.plusMinutes(codeExpirationMinutes);

        System.out.printf(currentTime + " current");
        System.out.printf(expirationTime + " expirationTime");

        if (currentTime.isAfter(expirationTime)) {
            userEmailVerification.setHasExpired(true);
            userEmailVerificationRepository.save(userEmailVerification);
            // Doğrulama kodu süresi geçmişse, kullanıcıyı uyarın veya kodun yeniden gönderilmesini sağlayın.
            return "Verification code has expired. Please request a new code.";
        }

        if (userEmailVerification.getVerificationCode().equals(verificationCode)) {
            // Doğrulama kodu doğruysa, kullanıcıyı doğrulama işlemi gerçekleştirin
            user.setVerified(true);
            userRepository.save(user);

            userEmailVerification.setHasExpired(true);
            userEmailVerificationRepository.save(userEmailVerification);
            return "User email verification successful.";
        } else {
            return "Invalid verification code.";
        }
    }
}
