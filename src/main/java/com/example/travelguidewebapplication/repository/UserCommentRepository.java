package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.enums.Status;
import com.example.travelguidewebapplication.model.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCommentRepository extends JpaRepository<UserComment, String> {

    //    @Query("SELECT u FROM UserCommentBox u " +
//            "INNER JOIN u.fkPlacesToVisitDetailsId d " +
//            "INNER JOIN d.places p " +
//            "WHERE p.id = :id")
//    List<UserCommentBox> getByPlacesId(Long id);
    @Query("SELECT u FROM UserComment u " +
            "INNER JOIN u.travelDestinationDetailsId d " +
            "INNER JOIN d.travelDestination p " +
            "WHERE p.id = :id AND p.status= :status")
    List<UserComment> findByFkPlacesToVisitDetailsIdPlacesId(String id, Status status);
}
