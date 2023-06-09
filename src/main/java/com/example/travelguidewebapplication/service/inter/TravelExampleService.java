package com.example.travelguidewebapplication.service.inter;

import java.util.List;

public interface TravelExampleService {
    List<ExampleTravel> findAll();

    void addTravel(ExampleTravel exampleTravel);
}
