package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.model.TravelPlaceKey;
import com.example.travelguidewebapplication.repository.TravelPlaceKeyRepository;
import com.example.travelguidewebapplication.service.inter.TravelPlaceKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelPlaceKeyServiceImpl implements TravelPlaceKeyService {
    private final TravelPlaceKeyRepository placeKeyRepository;

    @Override
    public TravelPlaceKey findByName(String value) {
        return placeKeyRepository.findTravelPlaceKeyByValue(value);
    }
}
