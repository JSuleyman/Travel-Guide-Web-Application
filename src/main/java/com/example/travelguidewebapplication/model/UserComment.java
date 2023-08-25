package com.example.travelguidewebapplication.model;

import com.example.travelguidewebapplication.util.CoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "user_comments")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserComment extends CoreEntity {
    String commentList;

    LocalDateTime localDateTime;

    Long commentReplyCount;

    @ManyToOne
    @JoinColumn(name = "travel_destination_details_id", referencedColumnName = "id", nullable = false)
    TravelDestinationDetails travelDestinationDetailsId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    User userId;

    @Getter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "userCommentId", fetch = FetchType.LAZY)
    List<UserCommentReply> userCommentReplyList;
}
