package com.example.travelguidewebapplication.travel_plan_test;


import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.util.CoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "is_answer_all_the_question")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IsAnswerAllTheQuestion extends CoreEntity {
    boolean isAnswerAllTheQuestion;

    @OneToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id", nullable = false)
    User user;
}
