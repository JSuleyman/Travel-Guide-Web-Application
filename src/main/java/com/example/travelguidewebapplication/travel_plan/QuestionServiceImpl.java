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
    private final AnswerToQuestionsRepository answerToQuestionsRepository;

    public List<QuestionsDTO> getAllQuestionWithAnswer() {
        List<Questions> questions = questionsRepository.findAll();
        List<QuestionsDTO> questionsDTOS = new ArrayList<>();

        for (Questions question : questions) {
            List<String> answers = answerToQuestionsRepository.findByQuestions(question);
            QuestionsDTO questionsDTO = QuestionsDTO.builder()
                    .question(question.getQuestion())
                    .answers(answers)
                    .build();
            questionsDTOS.add(questionsDTO);
        }

//        for (Map.Entry<String, List<String>> entry : questionsDTOS.entrySet()) {
//            for (String asdas : entry.getValue()) {
//                System.out.println(entry.getKey() + "value: " + asdas);
//            }
//        }

        return questionsDTOS;
    }
}
