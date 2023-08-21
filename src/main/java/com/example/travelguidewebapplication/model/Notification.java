package com.example.travelguidewebapplication.model;

import com.example.travelguidewebapplication.util.CoreEntity;
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
public class Notification extends CoreEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    String id;

    Integer fkUserId;

    String fkTravelDestinationId;

    String fkUserCommentId;

    boolean isNewComment;
}
