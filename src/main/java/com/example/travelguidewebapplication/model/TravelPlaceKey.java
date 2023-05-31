package com.example.travelguidewebapplication.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Entity
@Data
@Table(name = "travel_place_key")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TravelPlaceKey {
    @Id
    Long id;

    String key;

    String value;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "keyId", fetch = FetchType.LAZY)
    Collection<PlacesToVisit> placesToVisits;
}
