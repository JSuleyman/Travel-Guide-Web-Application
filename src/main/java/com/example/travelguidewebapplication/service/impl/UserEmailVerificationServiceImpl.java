package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.exception.InvalidVerificationCode;
import com.example.travelguidewebapplication.exception.NotFoundUser;
import com.example.travelguidewebapplication.exception.VerificationCodeHasExpired;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.model.UserEmailVerification;
import com.example.travelguidewebapplication.repository.UserEmailVerificationRepository;
import com.example.travelguidewebapplication.repository.UserRepository;
import com.example.travelguidewebapplication.service.inter.UserEmailVerificationService;
import com.example.travelguidewebapplication.util.VerificationCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEmailVerificationServiceImpl implements UserEmailVerificationService {

    private final UserEmailVerificationRepository userEmailVerificationRepository;
    private final UserRepository userRepository;
    private final EmailSenderServiceImpl emailSenderService;

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

        if (currentTime.isAfter(expirationTime)) {
            userEmailVerification.setHasExpired(true);
            userEmailVerificationRepository.save(userEmailVerification);
            // Doğrulama kodu süresi geçmişse, kullanıcıyı uyarın veya kodun yeniden gönderilmesini sağlayın.
            throw new VerificationCodeHasExpired(); // TODO bura 1 error elave ele //Testting
        }

        if (userEmailVerification.getVerificationCode().equals(verificationCode)) {
            // Doğrulama kodu doğruysa, kullanıcıyı doğrulama işlemi gerçekleştirin
            user.setVerified(true);
            userRepository.save(user);

            userEmailVerification.setHasExpired(true);
            userEmailVerificationRepository.save(userEmailVerification);
            return "User email verification successful.";
        } else {
            throw new InvalidVerificationCode(); //TODO burda da 2 inci error gonder //Testing
        }
    }

    @Override
    public void repeatSendVerificationCode(String email) {
        User user = userRepository.findByEmail(email).stream()
                .findFirst()
                .orElseThrow(NotFoundUser::new);

        if (!user.isVerified()) {
            String verificationCode = VerificationCodeGenerator.generateVerificationCode();

            List<UserEmailVerification> list = userEmailVerificationRepository.listUserById(user.getId());
            for (UserEmailVerification userEmailVerification : list) {
                userEmailVerification.setHasExpired(true);
                save(userEmailVerification);
            }

            var savedUserEmailVerification = UserEmailVerification.builder()
                    .verificationCode(verificationCode)
                    .user(user)
                    .verificationCodeCreatedAt(LocalDateTime.now())
                    .verificationCodeExpirationMinutes(1)
                    .hasExpired(false)
                    .build();
            save(savedUserEmailVerification);

            emailSenderService.sendEmail(email,
                    "Verification Email code",
                    verificationCode);
        }
    }
}
