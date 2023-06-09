package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.model.PlacesToVisitDetails;
import com.example.travelguidewebapplication.repository.PlacesToVisitDetailsRepository;
import com.example.travelguidewebapplication.service.inter.PlacesToVisitDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlacesToVisitDetailsServiceImpl implements PlacesToVisitDetailsService {

    private final PlacesToVisitDetailsRepository placesToVisitDetailsRepository;

    @Override
    public void save(PlacesToVisitDetails places) {
        placesToVisitDetailsRepository.save(places);
    }

    @Override
    public PlacesToVisitDetails getDetailsByPlacesId(Long placesId) {
       return placesToVisitDetailsRepository.findByPlacesId(placesId);
    }

    @Override
    public PlacesToVisitDetails getById(Long id) {
        return placesToVisitDetailsRepository.findById(id).orElseThrow();
    }
}
