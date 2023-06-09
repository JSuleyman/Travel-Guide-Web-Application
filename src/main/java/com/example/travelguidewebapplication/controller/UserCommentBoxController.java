package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.UserCommentBoxDTO;
import com.example.travelguidewebapplication.DTO.response.UserCommentBoxResponseDTO;
import com.example.travelguidewebapplication.service.inter.UserCommentBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_comment")
@RequiredArgsConstructor
public class UserCommentBoxController {
    private final UserCommentBoxService userCommentBoxService;

    @PostMapping
    public void add(@RequestBody UserCommentBoxDTO userCommentBoxDTO) {
        userCommentBoxService.save(userCommentBoxDTO);
    }

    @GetMapping("/details_id")
    public ResponseEntity<List<UserCommentBoxResponseDTO>> getUserCommentListByPlacesId(@RequestParam String fkPlacesId) {
        return ResponseEntity.ok(userCommentBoxService.getUserCommentListByPlacesId(fkPlacesId));
    }
}
