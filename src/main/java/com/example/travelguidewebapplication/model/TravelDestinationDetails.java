package com.example.travelguidewebapplication.model;

import com.example.travelguidewebapplication.util.CoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Table(name = "travel_destination_details")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TravelDestinationDetails extends CoreEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    String id;

    String travelDestinationDescription;

    String events;

    @OneToOne
    @JoinColumn(name = "travel_destination_id", referencedColumnName = "id", nullable = false)
    TravelDestination travelDestination;

    @Getter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "travelDestinationDetailsId", fetch = FetchType.LAZY)
    List<UserComment> userComments;
}
