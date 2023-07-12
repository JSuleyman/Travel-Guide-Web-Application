package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.response.NotificationResponseDTO;
import com.example.travelguidewebapplication.model.Notification;

import java.util.List;

public interface NotificationService {
    void save(Notification notification);

    List<NotificationResponseDTO> newCommentNotification();
}
