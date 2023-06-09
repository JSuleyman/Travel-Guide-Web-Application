package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.PlacesToVisit;
import com.example.travelguidewebapplication.model.PlacesToVisitDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacesToVisitDetailsRepository extends JpaRepository<PlacesToVisitDetails, String> {
    PlacesToVisitDetails findByPlacesId(String places_id);
}
