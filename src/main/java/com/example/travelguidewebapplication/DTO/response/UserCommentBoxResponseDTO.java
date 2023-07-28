package com.example.travelguidewebapplication.DTO.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCommentBoxResponseDTO {
    String id;
    String firstName;
    String lastName;
    String userMessage;
    LocalDateTime dateAndTime;
    Integer userId;
    Integer currentUserId;
}
