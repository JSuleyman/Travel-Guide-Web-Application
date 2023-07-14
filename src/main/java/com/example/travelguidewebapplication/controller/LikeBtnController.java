package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.LikeBtnDTO;
import com.example.travelguidewebapplication.service.inter.LikeBtnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like_btn")
@RequiredArgsConstructor
public class LikeBtnController {
    private final LikeBtnService service;

    @PostMapping("/add")
    public ResponseEntity<Long> add(@RequestBody LikeBtnDTO likeBtnDTO) {
        return ResponseEntity.ok(service.add(likeBtnDTO));
    }

    @GetMapping("/check/{rowId}")
    public ResponseEntity<Boolean> isLike(@PathVariable String rowId) {
        return ResponseEntity.ok(service.isLike(rowId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(@RequestBody LikeBtnDTO likeBtnDTO) {
        try {
            return ResponseEntity.ok(service.delete(likeBtnDTO));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(service.delete(likeBtnDTO));
        }
    }
}
