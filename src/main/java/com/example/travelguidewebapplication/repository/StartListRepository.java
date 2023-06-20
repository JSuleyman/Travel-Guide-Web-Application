package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.TravelDestination;
import com.example.travelguidewebapplication.model.StarList;
import com.example.travelguidewebapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StartListRepository extends JpaRepository<StarList, String> {
//    StarList findByPlacesId_Id(String id);

    StarList findByTravelDestination_IdAndUserId_Id(String placesId_id, Integer userId_id);

    StarList findByUserIdAndTravelDestination(User user, TravelDestination places);

    @Query("SELECT p.travelDestination FROM StarList p INNER JOIN p.userId k WHERE k.id = :userId")
    List<TravelDestination> findStarForUser(@Param("userId") String id);
}
