package com.example.travelguidewebapplication.travel_plan_test;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionsDTO {
    String question;
    List<String> answers;
}
