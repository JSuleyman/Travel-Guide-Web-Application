package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.service.inter.UserEmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
@RequiredArgsConstructor
public class UserEmailVerificationController {

    private final UserEmailVerificationService userEmailVerificationService;

    @PostMapping
    public ResponseEntity<String> verifyUser(@RequestParam String email,
                                             @RequestParam String verificationCode) {
        return ResponseEntity.ok(userEmailVerificationService.verifyUser(email, verificationCode));
    }

    @PostMapping("/send_repeat_verification_code")
    public void repeatSendVerificationCode(@RequestParam String email) {
        userEmailVerificationService.repeatSendVerificationCode(email);
    }
}
