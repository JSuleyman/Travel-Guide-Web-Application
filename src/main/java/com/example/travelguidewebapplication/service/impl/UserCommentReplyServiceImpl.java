package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.UserCommentReplyRequestDTO;
import com.example.travelguidewebapplication.model.UserComment;
import com.example.travelguidewebapplication.model.UserCommentReply;
import com.example.travelguidewebapplication.repository.UserCommentReplyRepository;
import com.example.travelguidewebapplication.repository.UserCommentRepository;
import com.example.travelguidewebapplication.service.inter.UserCommentReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCommentReplyServiceImpl implements UserCommentReplyService {
    private final UserCommentReplyRepository userCommentReplyRepository;
    private final UserCommentRepository userCommentRepository;

    @Override
    public void add(UserCommentReplyRequestDTO replyRequestDTO) {
        UserComment userComment = userCommentRepository.findById(replyRequestDTO.getCommentId()).orElseThrow();

        UserCommentReply userCommentReply = UserCommentReply.builder()
                .userCommentId(userComment)
                .replyCommentList(replyRequestDTO.getReplyMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        userCommentReplyRepository.save(userCommentReply);
    }

    @Override
    public List<UserCommentReply> findByCommentId(String commentId) {
        return userCommentReplyRepository.findByUserCommentIdId(commentId);
    }
}
