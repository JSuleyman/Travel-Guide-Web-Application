package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {

    @Query("""
            SELECT n FROM Notification n where n.isNewComment = true
            AND n.fkUserId = :userId
            """)
    List<Notification> findByFkUserId(Integer userId);
}
