package com.example.travelguidewebapplication.travel_plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, String> {
    @Query("""
            select q from AnswerToQuestions q
            INNER JOIN q.questions
            """)
    List<AnswerToQuestions> getAllQuestionWithAnswer();
}
