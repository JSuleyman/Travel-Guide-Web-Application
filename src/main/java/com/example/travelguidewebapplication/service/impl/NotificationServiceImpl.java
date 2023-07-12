package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.response.NotificationResponseDTO;
import com.example.travelguidewebapplication.model.Notification;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.NotificationRepository;
import com.example.travelguidewebapplication.repository.TravelDestinationRepository;
import com.example.travelguidewebapplication.service.inter.NotificationService;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            notificationResponseDTOS.add(NotificationResponseDTO.builder()
                    .destinationName(travelDestinationRepository.findById(notification.getFkTravelDestinationId()).get().getDestinationName())
                    .build());
        }
        return notificationResponseDTOS;
    }
}
