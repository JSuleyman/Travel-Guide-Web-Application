package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.response.PlacesToVisitDetailsResponseDTO;
import com.example.travelguidewebapplication.service.inter.TravelDestinationDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travel_destination_details")
@RequiredArgsConstructor
public class TravelDestinationDetailsController {
    private final TravelDestinationDetailsService travelDestinationDetailsService;

    @GetMapping
    public ResponseEntity<PlacesToVisitDetailsResponseDTO> getDetailsByPlacesId(@RequestParam String placesId) {
        return ResponseEntity.ok(travelDestinationDetailsService.getDetailsByPlacesId(placesId));
    }
}
