package com.example.travelguidewebapplication.travel_plan;

import com.example.travelguidewebapplication.util.CoreEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Entity
@Data
@Table(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Questions extends CoreEntity {
    String question;

    @OneToMany(mappedBy = "questions")
    Collection<AnswerToQuestions> answerToQuestions;
}
