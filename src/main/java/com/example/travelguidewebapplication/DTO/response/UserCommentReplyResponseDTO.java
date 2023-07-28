package com.example.travelguidewebapplication.DTO.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserCommentReplyResponseDTO {
    private String firstName;
    private String lastName;
    private String id;
    private LocalDateTime dateAndTime;
    private String replyCommentList;
    Integer userId;
    Integer currentUserId;
}
