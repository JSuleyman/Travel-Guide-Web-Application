package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.EmailSenderRequestDTO;
import com.example.travelguidewebapplication.service.impl.EmailSenderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailSenderController {

    private final EmailSenderServiceImpl emailSenderService;

    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailSenderRequestDTO emailSenderRequestDTO) {
        emailSenderService.sendEmail(emailSenderRequestDTO.getToEmail(),
                emailSenderRequestDTO.getSubject(),
                emailSenderRequestDTO.getBody());
    }
}
