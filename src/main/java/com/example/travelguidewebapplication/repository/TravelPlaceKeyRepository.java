package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.TravelPlaceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPlaceKeyRepository extends JpaRepository<TravelPlaceKey, String> {
//    @Query("SELECT k FROM TravelPlaceKey k WHERE k.key = :cityName")
    TravelPlaceKey findTravelPlaceKeyByValue(String cityName);

    @Query("SELECT k FROM TravelPlaceKey k WHERE k.key = :key")
    TravelPlaceKey findTravelPlaceKeyByKey(String key);
}
