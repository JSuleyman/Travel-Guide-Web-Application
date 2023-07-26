package com.example.travelguidewebapplication.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "icon_list")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TravelDestinationCardIconList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String iconName;

    @ManyToOne
    @JoinColumn(name = "travel_destination_id", referencedColumnName = "id", nullable = false)
    TravelDestination travelDestination;
}
