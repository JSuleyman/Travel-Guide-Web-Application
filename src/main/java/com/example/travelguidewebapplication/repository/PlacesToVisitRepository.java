package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.enums.Status;
import com.example.travelguidewebapplication.model.PlacesToVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacesToVisitRepository extends JpaRepository<PlacesToVisit, String> {

    @Query("SELECT p FROM PlacesToVisit p INNER JOIN p.keyId k WHERE k.key = :key AND p.status = :status")
    List<PlacesToVisit> findByTravelPlaceKeyValue(@Param("key") String key, @Param("status") Status status);

    @Query("SELECT p FROM PlacesToVisit p WHERE p.createdBy = :userId" +
            " AND (:status IS NULL OR p.status = :status)")
    List<PlacesToVisit> createdByUserList(@Param("userId") Integer id, @Param("status") Status status);


    @Query("SELECT count(p.id) FROM PlacesToVisit p WHERE p.createdBy = :userId" +
            " AND (:status IS NULL OR p.status IN :status)")
    Integer createdByUserListStatusCount(@Param("userId") Integer id, @Param("status") Status status);

}
