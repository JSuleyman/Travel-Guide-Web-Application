package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.LikeBtn;
import com.example.travelguidewebapplication.model.PlacesToVisit;
import com.example.travelguidewebapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeBtnRepository extends JpaRepository<LikeBtn, Long> {
    LikeBtn findByPlacesId_Id(Long id);

    LikeBtn findByPlacesId_IdAndUserId_Id(Long placesId_id, Integer userId_id);

    LikeBtn findByUserIdAndPlacesId(User user, PlacesToVisit places);

//    @Query("SELECT p.placesId FROM LikeBtn p INNER JOIN p.userId k WHERE k.id = :userId")
//    List<PlacesToVisit> findStarForUser(@Param("userId") Long id);
}
