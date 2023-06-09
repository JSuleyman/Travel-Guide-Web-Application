package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.model.TravelPlaceKey;

import java.util.List;

public interface TravelPlaceKeyService {
    TravelPlaceKey findByName(String value);

    List<TravelPlaceKey> getAll();

    void add(TravelPlaceKey travelPlaceKey);
}
