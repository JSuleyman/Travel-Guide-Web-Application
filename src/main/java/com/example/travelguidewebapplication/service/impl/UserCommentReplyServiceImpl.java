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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
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

        Calendar azerbaijanCalendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Baku"));
        int year = azerbaijanCalendar.get(Calendar.YEAR);
        int month = azerbaijanCalendar.get(Calendar.MONTH) + 1;  // Ay, 0-indeksli olduğu üçün +1 olur
        int day = azerbaijanCalendar.get(Calendar.DAY_OF_MONTH);
        int hour = azerbaijanCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = azerbaijanCalendar.get(Calendar.MINUTE);
        int second = azerbaijanCalendar.get(Calendar.SECOND);

        UserCommentReply userCommentReply = UserCommentReply.builder()
                .userCommentId(userComment)
                .replyCommentList(replyRequestDTO.getReplyMessage())
                .localDateTime(LocalDateTime.of(year, month, day, hour, minute, second))
                .userId(userService.getCurrentUser())
                .build();
        userCommentReplyRepository.save(userCommentReply);
    }

    public List<UserCommentReplyResponseDTO> findByCommentId(String commentId, int first, int offset) {
        Pageable pageable = PageRequest.of(first, offset);
        List<UserCommentReply> userCommentReplies = userCommentReplyRepository.findByUserCommentIdId(commentId, pageable);
        return userCommentReplies.stream()
                .map(this::mapToUserCommentReplyListResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getReplyCommentCount(String commentId) {
        return findByCommentIdForCount(commentId);
    }

    private Integer findByCommentIdForCount(String commentId) {
        List<UserCommentReply> userCommentReplies = userCommentReplyRepository.findByUserCommentIdId(commentId);
        return userCommentReplies.size();
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
