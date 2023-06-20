package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.UserCommentReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommentReplyRepository extends JpaRepository<UserCommentReply, String> {

}
