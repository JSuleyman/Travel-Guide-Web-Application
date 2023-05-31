package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.model.TravelPlaceKey;
import com.example.travelguidewebapplication.repository.TravelPlaceKeyRepository;
import com.example.travelguidewebapplication.service.inter.TravelPlaceKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tavel_place")
@RestController
@RequiredArgsConstructor
public class TravelPlaceKeyController {
    private final TravelPlaceKeyService placeKeyService;
    private final TravelPlaceKeyRepository travelPlaceKeyRepository;

    @GetMapping("/get")
    public ResponseEntity<TravelPlaceKey> findByValue(@RequestParam String cityName) {
        return ResponseEntity.ok(placeKeyService.findByName(cityName));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TravelPlaceKey>> findAll() {
        return ResponseEntity.ok(travelPlaceKeyRepository.findAll());
    }
}
