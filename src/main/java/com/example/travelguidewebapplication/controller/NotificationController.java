package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.response.NotificationResponseDTO;
import com.example.travelguidewebapplication.service.inter.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> testNotification() {
        List<NotificationResponseDTO> notifications = notificationService.newCommentNotification();
        messagingTemplate.convertAndSend("/topic/notifications", notifications);
        return ResponseEntity.ok(notifications);
    }
}
