package com.example.travelguidewebapplication.travel_plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerToQuestionsRepository extends JpaRepository<AnswerToQuestions,String> {
    @Query("""
            SELECT a.answer from AnswerToQuestions a where a.questions = :questions
             ORDER BY a.answer ASC
            """)
    List<String> findByQuestions(Questions questions);
}
