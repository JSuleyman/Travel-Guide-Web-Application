package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.UserCommentReplyRequestDTO;
import com.example.travelguidewebapplication.DTO.response.UserCommentReplyResponseDTO;
import com.example.travelguidewebapplication.service.inter.UserCommentReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_comment_reply")
@RequiredArgsConstructor
public class UserCommentReplyController {
    private final UserCommentReplyService userCommentReplyService;

    @PostMapping
    public void add(@RequestBody UserCommentReplyRequestDTO userCommentReplyRequestDTO) {
        userCommentReplyService.add(userCommentReplyRequestDTO);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<List<UserCommentReplyResponseDTO>> findByCommentId(@PathVariable String commentId,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size) {
        List<UserCommentReplyResponseDTO> userCommentReplies = userCommentReplyService.findByCommentId(commentId, page, size);
        return ResponseEntity.ok(userCommentReplies);
    }


    @GetMapping("/count/{commentId}")
    public ResponseEntity<Integer> getReplyCommentCount(@PathVariable String commentId) {
        return ResponseEntity.ok(userCommentReplyService.getReplyCommentCount(commentId));
    }
}
