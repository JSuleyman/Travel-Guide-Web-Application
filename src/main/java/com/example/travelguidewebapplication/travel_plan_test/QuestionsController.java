package com.example.travelguidewebapplication.travel_plan_test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions_answer")
@RequiredArgsConstructor
public class QuestionsController {
    private final QuestionServiceImpl questionService;

    @GetMapping
    public ResponseEntity<List<QuestionsDTO>> getQuestionService() {
        return ResponseEntity.ok(questionService.getAllQuestionWithAnswer());
    }
}