package com.example.travelguidewebapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl {

    private final JavaMailSender javaMailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("travel.guide.notification@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject + " (Bu e-poçt real deyil!!!)");

        javaMailSender.send(message);
    }
}
