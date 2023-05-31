package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.PlacesToVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacesToVisitRepository extends JpaRepository<PlacesToVisit, Long> {

    @Query("SELECT p FROM PlacesToVisit p INNER JOIN p.keyId k WHERE k.key = :key")
    List<PlacesToVisit> findByTravelPlaceKeyValue(@Param("key") String key);
}
