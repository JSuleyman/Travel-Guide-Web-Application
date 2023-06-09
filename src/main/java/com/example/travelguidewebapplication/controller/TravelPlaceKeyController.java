package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.model.TravelPlaceKey;
import com.example.travelguidewebapplication.service.inter.TravelPlaceKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tavel_place")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TravelPlaceKeyController {
    private final TravelPlaceKeyService placeKeyService;

    @GetMapping("/get")
    public ResponseEntity<TravelPlaceKey> findByValue(@RequestParam String cityName) {
        return ResponseEntity.ok(placeKeyService.findByName(cityName));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TravelPlaceKey>> findAll() {
        return ResponseEntity.ok(placeKeyService.getAll());
    }

    @PostMapping
    public void add(@RequestBody TravelPlaceKey placeKey) {
        log.info(String.valueOf(placeKey));
        placeKeyService.add(placeKey);
    }
}
