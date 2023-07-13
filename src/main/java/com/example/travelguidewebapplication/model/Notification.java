package com.example.travelguidewebapplication.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "comment_notifications")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    Integer fkUserId;

    String fkTravelDestinationId;

    String fkUserCommentId;

    boolean isNewComment;
}
