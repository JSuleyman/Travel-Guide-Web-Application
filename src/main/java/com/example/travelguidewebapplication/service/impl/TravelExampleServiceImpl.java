package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.repository.ExampleTravelRepository;
import com.example.travelguidewebapplication.service.inter.TravelExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelExampleServiceImpl implements TravelExampleService {

    private final ExampleTravelRepository exampleTravelRepository;

    @Override
    public List<ExampleTravel> findAll() {
        return exampleTravelRepository.findAll();
    }

    @Override
    public void addTravel(ExampleTravel exampleTravel) {
        exampleTravelRepository.save(exampleTravel);
    }
}
