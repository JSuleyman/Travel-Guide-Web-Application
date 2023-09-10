package com.example.travelguidewebapplication.travel_plan;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.rmi.server.LogStream.log;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl {
    private final QuestionsRepository questionsRepository;

    public HashMap<String, String> getAllQuestionWithAnswer() {
        log.info("Testtt: " + questionsRepository.getAllQuestionWithAnswer());
        List<AnswerToQuestions> answerToQuestions = questionsRepository.getAllQuestionWithAnswer();
        log.info("Questionsss: " + answerToQuestions.get(0).getQuestions());
        HashMap<String, String> questionsDTOS = new HashMap<>();

        for (AnswerToQuestions answerToQuestions1 : answerToQuestions) {
            questionsDTOS.put(answerToQuestions1.getQuestions().getQuestion(), answerToQuestions1.getAnswer());
        }

        for (Map.Entry<String, String> entry : questionsDTOS.entrySet()) {
            System.out.println(entry.getKey() + "value: " + entry.getValue());
        }

        return questionsDTOS;
    }
}
