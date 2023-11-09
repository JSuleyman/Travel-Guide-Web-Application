package com.example.travelguidewebapplication.travel_plan_test;


import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/is_answer")
@RequiredArgsConstructor
public class IsAnswerAllTheQuestionController {
    private final UserService userService;
    private final IsAnswerAllTheQuestionRepository repository;

    @PostMapping
    public void isAnswerAllTheQuestion() {
        IsAnswerAllTheQuestion isAnswerAllTheQuestion = repository.findByUser(userService.getCurrentUser());
        if (isAnswerAllTheQuestion != null) {
            throw new RuntimeException();
        }
        repository.save(IsAnswerAllTheQuestion.builder()
                .user(userService.getCurrentUser())
                .isAnswerAllTheQuestion(true)
                .build());
    }

    @GetMapping
    public ResponseEntity<Boolean> isAnswerAllTheQuestionCheck() {
        IsAnswerAllTheQuestion isAnswerAllTheQuestion = repository.findByUser(userService.getCurrentUser());
        return ResponseEntity.ok(isAnswerAllTheQuestion.isAnswerAllTheQuestion());
    }

}
