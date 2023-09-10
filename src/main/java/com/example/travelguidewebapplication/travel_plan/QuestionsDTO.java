package com.example.travelguidewebapplication.travel_plan;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionsDTO {
    HashMap<String, String> questionsWithAnswer;
}
