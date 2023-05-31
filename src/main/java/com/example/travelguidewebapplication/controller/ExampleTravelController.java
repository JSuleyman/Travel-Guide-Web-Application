package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.model.ExampleTravel;
import com.example.travelguidewebapplication.service.inter.TravelExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travels")
@RequiredArgsConstructor
public class ExampleTravelController {
    private final TravelExampleService travelExampleService;

    @GetMapping("/get")
    public ResponseEntity<List<ExampleTravel>> getAll() {
        return ResponseEntity.ok(travelExampleService.findAll());
    }


    @PostMapping("/add")
    void add(@RequestBody ExampleTravel exampleTravel) {
        travelExampleService.addTravel(exampleTravel);
    }
}
