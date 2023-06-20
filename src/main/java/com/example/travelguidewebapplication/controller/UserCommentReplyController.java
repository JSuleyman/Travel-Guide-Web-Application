package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.UserCommentReplyRequestDTO;
import com.example.travelguidewebapplication.service.inter.UserCommentReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_comment_reply")
@RequiredArgsConstructor
public class UserCommentReplyController {
    private final UserCommentReplyService userCommentReplyService;

    @PostMapping
    public void add(@RequestBody UserCommentReplyRequestDTO userCommentReplyRequestDTO) {
        userCommentReplyService.add(userCommentReplyRequestDTO);
    }
}
