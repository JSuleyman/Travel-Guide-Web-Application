package com.example.travelguidewebapplication.model;

import com.example.travelguidewebapplication.util.CoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Entity
@Data
@Table(name = "travel_place_category")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TravelPlaceCategory extends CoreEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    String id;

    String category;

    String description;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    Collection<TravelDestination> travelDestinations;
}
