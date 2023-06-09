package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.PlacesToVisit;
import com.example.travelguidewebapplication.model.StarList;
import com.example.travelguidewebapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StartListRepository extends JpaRepository<StarList, String> {
    StarList findByPlacesId_Id(String id);

    StarList findByPlacesId_IdAndUserId_Id(String placesId_id, Integer userId_id);

    StarList findByUserIdAndPlacesId(User user, PlacesToVisit places);

    @Query("SELECT p.placesId FROM StarList p INNER JOIN p.userId k WHERE k.id = :userId")
    List<PlacesToVisit> findStarForUser(@Param("userId") String id);
}
