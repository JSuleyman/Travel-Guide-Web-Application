package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.model.PlacesToVisitDetails;

public interface PlacesToVisitDetailsService {
    void save(PlacesToVisitDetails places);

    PlacesToVisitDetails getDetailsByPlacesId(Long placesId);

    PlacesToVisitDetails getById(Long id);
}
