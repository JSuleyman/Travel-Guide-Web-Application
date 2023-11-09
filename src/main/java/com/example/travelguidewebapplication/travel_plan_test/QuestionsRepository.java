package com.example.travelguidewebapplication.travel_plan_test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, String> {
//    @Query("""
//            select q from AnswerToQuestions q
//            INNER JOIN q.questions
//            """)
//    List<AnswerToQuestions> getAllQuestionWithAnswer();
}
