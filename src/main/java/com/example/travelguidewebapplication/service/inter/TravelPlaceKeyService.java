package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.model.TravelPlaceKey;

public interface TravelPlaceKeyService {
    TravelPlaceKey findByName(String value);
}
