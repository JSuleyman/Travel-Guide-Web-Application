package com.example.travelguidewebapplication.travel_plan_test;

import com.example.travelguidewebapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsAnswerAllTheQuestionRepository extends JpaRepository<IsAnswerAllTheQuestion, String> {
    IsAnswerAllTheQuestion findByUser(User user);
}
