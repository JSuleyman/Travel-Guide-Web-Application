package com.example.travelguidewebapplication.model;

import com.example.travelguidewebapplication.enums.Status;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_status")
    Status status;

    String places;

    Integer manyForTravel;

    String imageUrl;

    Long likeCount;

    @Column(nullable = false)
    boolean isCreatedByUser;

    Integer createdBy;

    String createdByName;

    @JoinColumn(name = "key_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    TravelPlaceKey keyId;
}
