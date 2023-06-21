package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.UserCommentReplyRequestDTO;
import com.example.travelguidewebapplication.model.UserCommentReply;
import com.example.travelguidewebapplication.service.inter.UserCommentReplyService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<UserCommentReply>> findByCommentId(@PathVariable String commentId) {
        return ResponseEntity.ok(userCommentReplyService.findByCommentId(commentId));
    }
}
