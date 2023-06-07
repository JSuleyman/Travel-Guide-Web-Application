package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.UserCustomCardRequestDTO;
import com.example.travelguidewebapplication.model.PlacesToVisit;
import com.example.travelguidewebapplication.service.inter.PlacesToVisitService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places_visit")
@RequiredArgsConstructor
public class PlacesToVisitController {
    private final PlacesToVisitService placesToVisitService;

    @GetMapping("/get")
    public ResponseEntity<List<PlacesToVisit>> getByValue(@RequestParam String key) {
        return ResponseEntity.ok(placesToVisitService.getByValue(key));
    }

    @PostMapping
    public void add(@RequestBody UserCustomCardRequestDTO userCustomCardRequestDTO) {
        placesToVisitService.userCustomCard(userCustomCardRequestDTO);
    }

    @GetMapping("created_by_user_list")
    public ResponseEntity<List<PlacesToVisit>> createdByUserList() {
        return ResponseEntity.ok(placesToVisitService.createdByUserList());
    }
}
