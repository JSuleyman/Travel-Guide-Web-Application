package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.model.TravelPlaceCategory;
import com.example.travelguidewebapplication.repository.TravelPlaceCategoryRepository;
import com.example.travelguidewebapplication.service.inter.TravelPlaceCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelPlaceCategoryServiceImpl implements TravelPlaceCategoryService {
    private final TravelPlaceCategoryRepository travelPlaceCategoryRepository;

    @Override
    public TravelPlaceCategory findByName(String value) {
        return travelPlaceCategoryRepository.findTravelPlaceKeyByDescription(value);
    }

    @Override
    public List<TravelPlaceCategory> getAll() {
        return travelPlaceCategoryRepository.findAll();
    }

    @Override
    public void add(TravelPlaceCategory travelPlaceCategory) {
        travelPlaceCategoryRepository.save(travelPlaceCategory);
    }
}
