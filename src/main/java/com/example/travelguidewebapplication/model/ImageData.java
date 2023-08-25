package com.example.travelguidewebapplication.model;

import com.example.travelguidewebapplication.util.CoreEntity;
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
public class ImageData extends CoreEntity {
    private String name;

    private String type;

    @Lob
    @Column(name = "image_data", length = 100000)
    private byte[] imageData;

    @JoinColumn(name = "travel_destination_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    TravelDestination travelDestinationId;
}
