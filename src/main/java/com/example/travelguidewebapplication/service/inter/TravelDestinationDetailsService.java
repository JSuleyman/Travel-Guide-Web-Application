package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.response.PlacesToVisitDetailsResponseDTO;
import com.example.travelguidewebapplication.model.TravelDestinationDetails;

public interface TravelDestinationDetailsService {
    void save(TravelDestinationDetails places);

    PlacesToVisitDetailsResponseDTO getDetailsByPlacesId(String placesId);

    TravelDestinationDetails getById(String id);
}
