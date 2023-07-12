package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.model.Notification;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.NotificationRepository;
import com.example.travelguidewebapplication.service.inter.NotificationService;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserService userService;

    @Override
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> newCommentNotification() {
        User user = userService.getCurrentUser();
        List<Notification> notification = notificationRepository.findByFkUserId(user.getId());

        return notification;
    }
}
