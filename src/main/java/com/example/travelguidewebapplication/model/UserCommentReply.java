package com.example.travelguidewebapplication.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_comment_reply")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserCommentReply {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String replyCommentList;

    LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "user_comment_id", referencedColumnName = "id", nullable = false)
    UserComment userCommentId;
}
