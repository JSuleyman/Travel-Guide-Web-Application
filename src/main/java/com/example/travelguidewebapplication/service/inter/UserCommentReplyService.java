package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.UserCommentReplyRequestDTO;
import com.example.travelguidewebapplication.DTO.response.UserCommentReplyResponseDTO;
import com.example.travelguidewebapplication.model.UserCommentReply;

import java.util.List;

public interface UserCommentReplyService {
    void add(UserCommentReplyRequestDTO replyRequestDTO);

    List<UserCommentReplyResponseDTO> findByCommentId(String commentId);
}
