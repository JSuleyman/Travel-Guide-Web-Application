package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.model.TravelPlaceCategory;

import java.util.List;

public interface TravelPlaceCategoryService {
    TravelPlaceCategory findByName(String value);

    List<TravelPlaceCategory> getAll();

    void add(TravelPlaceCategory travelPlaceCategory);
}
