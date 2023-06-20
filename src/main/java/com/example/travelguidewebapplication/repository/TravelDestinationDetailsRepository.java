package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.TravelDestinationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelDestinationDetailsRepository extends JpaRepository<TravelDestinationDetails, String> {
    TravelDestinationDetails findByTravelDestinationId(String places_id);
}
