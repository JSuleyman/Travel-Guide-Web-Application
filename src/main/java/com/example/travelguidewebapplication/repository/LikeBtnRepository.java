package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.LikeBtn;
import com.example.travelguidewebapplication.model.TravelDestination;
import com.example.travelguidewebapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeBtnRepository extends JpaRepository<LikeBtn, String> {
//    LikeBtn findByPlacesId_Id(String id);

    LikeBtn findByTravelDestination_IdAndUserId_Id(String placesId_id, Integer userId_id);

    LikeBtn findByUserIdAndTravelDestination(User user, TravelDestination places);

//    @Query("SELECT p.placesId FROM LikeBtn p INNER JOIN p.userId k WHERE k.id = :userId")
//    List<PlacesToVisit> findStarForUser(@Param("userId") Long id);
}
