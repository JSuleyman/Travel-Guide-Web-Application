package com.example.travelguidewebapplication.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_comment_box")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserCommentBox {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String messagesList;

    LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "fk_places_to_visit_details_id", referencedColumnName = "id", nullable = false)
    PlacesToVisitDetails fkPlacesToVisitDetailsId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id", nullable = false)
    User fkUserId;
}
