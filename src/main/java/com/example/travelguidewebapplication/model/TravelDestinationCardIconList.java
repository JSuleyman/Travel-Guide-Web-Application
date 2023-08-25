package com.example.travelguidewebapplication.model;

import com.example.travelguidewebapplication.util.CoreEntity;
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
public class TravelDestinationCardIconList extends CoreEntity {
    String iconName;

    @ManyToOne
    @JoinColumn(name = "travel_destination_id", referencedColumnName = "id", nullable = false)
    TravelDestination travelDestination;
}
