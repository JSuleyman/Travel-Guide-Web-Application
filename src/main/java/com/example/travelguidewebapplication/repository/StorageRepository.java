package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.ImageData;
import com.example.travelguidewebapplication.model.TravelDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<ImageData, String> {
    Optional<ImageData> findByNameAndAndTravelDestinationId_Id(String fileName, String fkPlacesId);

    List<ImageData> findByTravelDestinationId(TravelDestination places);

    List<ImageData> findByTravelDestinationId_Id(String placesId);
}
