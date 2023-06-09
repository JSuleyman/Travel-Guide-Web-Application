package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.model.PlacesToVisitDetails;
import com.example.travelguidewebapplication.service.inter.PlacesToVisitDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/places_visit_details")
@RequiredArgsConstructor
public class PlacesToVisitDetailsController {
    private final PlacesToVisitDetailsService placesToVisitDetailsService;

    @GetMapping
    public ResponseEntity<PlacesToVisitDetails> getDetailsByPlacesId(@RequestParam String placesId) {
        return ResponseEntity.ok(placesToVisitDetailsService.getDetailsByPlacesId(placesId));
    }
}
