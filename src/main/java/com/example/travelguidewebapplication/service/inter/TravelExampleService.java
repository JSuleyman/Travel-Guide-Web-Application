package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.model.ExampleTravel;

import java.util.List;

public interface TravelExampleService {
    List<ExampleTravel> findAll();

    void addTravel(ExampleTravel exampleTravel);
}
