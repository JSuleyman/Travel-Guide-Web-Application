package com.example.travelguidewebapplication.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "places_to_visit")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PlacesToVisit {
    @Id
    @GeneratedValue
    Long id;

    String places;

    Integer manyForTravel;

    String imageUrl;

    Long likeCount;

    String userComments;

    String events;

    @Column(nullable = false)
    boolean isCreatedByUser;

    Integer createdBy;

    String createdByName;

    @JoinColumn(name = "key_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    TravelPlaceKey keyId;
}
