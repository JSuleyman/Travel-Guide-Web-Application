package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.response.NotificationResponseDTO;
import com.example.travelguidewebapplication.model.Notification;
import com.example.travelguidewebapplication.model.TravelDestination;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.NotificationRepository;
import com.example.travelguidewebapplication.repository.TravelDestinationRepository;
import com.example.travelguidewebapplication.service.inter.NotificationService;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserService userService;
    private final TravelDestinationRepository travelDestinationRepository;

    @Override
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationResponseDTO> newCommentNotification() {
        User user = userService.getCurrentUser();
        List<Notification> notifications = notificationRepository.findByFkUserId(user.getId());
        List<NotificationResponseDTO> notificationResponseDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            Optional<TravelDestination> travelDestination = travelDestinationRepository.findById(notification.getFkTravelDestinationId());
            travelDestination.ifPresent(destination -> notificationResponseDTOS.add(NotificationResponseDTO.builder()
                    .destinationName(destination.getDestinationName())
                    .comment(notification.getComment())
                    .build()));

        }
        return notificationResponseDTOS;
    }

    @Override
    public void notificationFalse() {
        User user = userService.getCurrentUser();
        List<Notification> notifications = notificationRepository.findByFkUserId(user.getId());

        for (Notification notification : notifications) {
            notification.setNewComment(false);
            notificationRepository.save(notification);
        }
    }
}
