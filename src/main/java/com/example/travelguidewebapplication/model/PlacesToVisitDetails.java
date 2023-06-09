package com.example.travelguidewebapplication.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Table(name = "places_to_visit_details")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PlacesToVisitDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String userComments;

    String events;

    @OneToOne
    @JoinColumn(name = "fk_places_to_visit_id", referencedColumnName = "id", nullable = false)
    PlacesToVisit places;

    @Getter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "fkPlacesToVisitDetailsId", fetch = FetchType.LAZY)
    List<UserCommentBox> userCommentBoxes;
}
