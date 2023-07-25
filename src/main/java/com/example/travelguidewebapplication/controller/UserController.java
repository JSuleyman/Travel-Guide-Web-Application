package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.UserFullNameUpdateRequestDTO;
import com.example.travelguidewebapplication.DTO.response.UserFullNameUpdateResponseDTO;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_profile")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/full_name")
    public ResponseEntity<UserFullNameUpdateResponseDTO> updateUserFullName(
            @RequestBody UserFullNameUpdateRequestDTO userFullNameUpdateRequestDTO) {
        return ResponseEntity.ok(userService.updateUserFullName(userFullNameUpdateRequestDTO));
    }
}
