package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.UserCommentDTO;
import com.example.travelguidewebapplication.DTO.response.UserCommentBoxResponseDTO;
import com.example.travelguidewebapplication.service.inter.UserCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_comment")
@RequiredArgsConstructor
public class UserCommentController {
    private final UserCommentService userCommentService;

    @PostMapping
    public void add(@RequestBody UserCommentDTO userCommentDTO) {
        userCommentService.save(userCommentDTO);
    }

    @GetMapping("/details_id")
    public ResponseEntity<List<UserCommentBoxResponseDTO>> getUserCommentListByPlacesId(
            @RequestParam String fkPlacesId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(userCommentService.getUserCommentListByPlacesId(fkPlacesId, page, size));
    }

    @GetMapping("/current_user_id")
    public ResponseEntity<Integer> getCurrenctUserId() {
        return ResponseEntity.ok(userCommentService.currentUserId());
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> commentCountByTravelDestinationId(
            @RequestParam String travelDestinationId) {
        return ResponseEntity
                .ok(userCommentService.commentCountByTravelDestinationId(travelDestinationId));
    }
}
