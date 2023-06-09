package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.model.PlacesToVisitDetails;

public interface PlacesToVisitDetailsService {
    void save(PlacesToVisitDetails places);

    PlacesToVisitDetails getDetailsByPlacesId(String placesId);

    PlacesToVisitDetails getById(String id);
}
