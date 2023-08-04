package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.model.UserEmailVerification;

public interface UserEmailVerificationService {
    void save(UserEmailVerification userEmailVerification);

    String verifyUser(String email, String verificationCode);

    void repeatSendVerificationCode(String email);
}
