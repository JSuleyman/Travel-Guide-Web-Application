package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.model.Notification;
import com.example.travelguidewebapplication.service.inter.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> testNotification() {
        return ResponseEntity.ok(notificationService.newCommentNotification());
    }
}
