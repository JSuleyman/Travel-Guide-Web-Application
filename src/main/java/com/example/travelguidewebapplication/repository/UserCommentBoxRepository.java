package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.enums.Status;
import com.example.travelguidewebapplication.model.PlacesToVisitDetails;
import com.example.travelguidewebapplication.model.UserCommentBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCommentBoxRepository extends JpaRepository<UserCommentBox, String> {

    //    @Query("SELECT u FROM UserCommentBox u " +
//            "INNER JOIN u.fkPlacesToVisitDetailsId d " +
//            "INNER JOIN d.places p " +
//            "WHERE p.id = :id")
//    List<UserCommentBox> getByPlacesId(Long id);
    @Query("SELECT u FROM UserCommentBox u " +
            "INNER JOIN u.fkPlacesToVisitDetailsId d " +
            "INNER JOIN d.places p " +
            "WHERE p.id = :id AND p.status= :status")
    List<UserCommentBox> findByFkPlacesToVisitDetailsIdPlacesId(String id, Status status);
}
