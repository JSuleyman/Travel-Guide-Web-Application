package com.example.travelguidewebapplication.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "start_list")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StarList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "is_favorite")
    boolean isFavorite;

    @JoinColumn(name = "travel_destination_id", referencedColumnName = "id", nullable = false)
    @OneToOne
    TravelDestination travelDestination;

    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    User userId;
}
