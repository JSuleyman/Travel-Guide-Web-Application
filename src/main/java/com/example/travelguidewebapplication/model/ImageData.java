package com.example.travelguidewebapplication.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "image_data")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    private String name;

    private String type;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    @JoinColumn(name = "travel_destination_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    TravelDestination travelDestinationId;
}
