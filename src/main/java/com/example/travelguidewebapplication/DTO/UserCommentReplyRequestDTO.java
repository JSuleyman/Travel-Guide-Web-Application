package com.example.travelguidewebapplication.DTO;

import lombok.Data;

@Data
public class UserCommentReplyRequestDTO {
    String commentId;
    String replyMessage;
}
