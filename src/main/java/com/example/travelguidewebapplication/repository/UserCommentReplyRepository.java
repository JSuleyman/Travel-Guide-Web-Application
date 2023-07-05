package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.UserCommentReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCommentReplyRepository extends JpaRepository<UserCommentReply, String> {
//    @Query("SELECT u FROM UserCommentReply u where u.userCommentId.id= :id")
    List<UserCommentReply> findByUserCommentIdId(@Param("id") String id);

    List<UserCommentReply> findByUserCommentIdId(String id, Pageable pageable);
}
