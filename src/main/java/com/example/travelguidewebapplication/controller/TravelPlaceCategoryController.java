package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.model.TravelPlaceCategory;
import com.example.travelguidewebapplication.service.inter.TravelPlaceCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tavel_place")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TravelPlaceCategoryController {
    private final TravelPlaceCategoryService placeKeyService;

    @GetMapping("/get")
    public ResponseEntity<TravelPlaceCategory> findByValue(@RequestParam String cityName) {
        return ResponseEntity.ok(placeKeyService.findByName(cityName));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TravelPlaceCategory>> findAll() {
        return ResponseEntity.ok(placeKeyService.getAll());
    }

    @PostMapping
    public void add(@RequestBody TravelPlaceCategory placeKey) {
        log.info(String.valueOf(placeKey));
        placeKeyService.add(placeKey);
    }
}
