package com.example.travelguidewebapplication.travel_plan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/questions_answer")
@RequiredArgsConstructor
public class QuestionsController {
    private final QuestionServiceImpl questionService;

    @GetMapping
    public ResponseEntity<HashMap<String,String>> getQuestionService() {
        return ResponseEntity.ok(questionService.getAllQuestionWithAnswer());
    }
}