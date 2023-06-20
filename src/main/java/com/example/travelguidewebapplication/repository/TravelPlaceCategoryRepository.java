package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.TravelPlaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPlaceCategoryRepository extends JpaRepository<TravelPlaceCategory, String> {
//    @Query("SELECT k FROM TravelPlaceKey k WHERE k.key = :cityName")
    TravelPlaceCategory findTravelPlaceKeyByDescription(String cityName);

    @Query("SELECT k FROM TravelPlaceCategory k WHERE k.category = :key")
    TravelPlaceCategory findTravelPlaceKeyByKey(String key);
}
