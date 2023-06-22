package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.UserCommentReplyRequestDTO;
import com.example.travelguidewebapplication.DTO.response.UserCommentReplyResponseDTO;
import com.example.travelguidewebapplication.model.UserComment;
import com.example.travelguidewebapplication.model.UserCommentReply;
import com.example.travelguidewebapplication.repository.UserCommentReplyRepository;
import com.example.travelguidewebapplication.repository.UserCommentRepository;
import com.example.travelguidewebapplication.service.inter.UserCommentReplyService;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommentReplyServiceImpl implements UserCommentReplyService {
    private final UserCommentReplyRepository userCommentReplyRepository;
    private final UserCommentRepository userCommentRepository;
    private final UserService userService;

    @Override
    public void add(UserCommentReplyRequestDTO replyRequestDTO) {
        UserComment userComment = userCommentRepository.findById(replyRequestDTO.getCommentId()).orElseThrow();

        UserCommentReply userCommentReply = UserCommentReply.builder()
                .userCommentId(userComment)
                .replyCommentList(replyRequestDTO.getReplyMessage())
                .localDateTime(LocalDateTime.now())
                .userId(userService.getCurrentUser())
                .build();
        userCommentReplyRepository.save(userCommentReply);
    }

    @Override
    public List<UserCommentReplyResponseDTO> findByCommentId(String commentId) {
        List<UserCommentReply> userCommentReplies = userCommentReplyRepository.findByUserCommentIdId(commentId);
        return userCommentReplies.stream()
                .map(this::mapToUserCommentReplyListResponseDTO)
                .collect(Collectors.toList());
    }

    private UserCommentReplyResponseDTO mapToUserCommentReplyListResponseDTO(UserCommentReply userCommentReply) {
        return UserCommentReplyResponseDTO.builder()
                .id(userCommentReply.getId())
                .firstName(userCommentReply.getUserId().getFirstname())
                .lastName(userCommentReply.getUserId().getLastname())
                .dateAndTime(userCommentReply.getLocalDateTime())
                .replyCommentList(userCommentReply.getReplyCommentList())
                .userId(userCommentReply.getUserId().getId())
                .build();
    }
}
