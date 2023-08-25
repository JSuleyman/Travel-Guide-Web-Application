package com.example.travelguidewebapplication.model;

import com.example.travelguidewebapplication.enums.Status;
import com.example.travelguidewebapplication.util.CoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Data
@Table(name = "travel_destinations")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TravelDestination extends CoreEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "card_status")
    Status status;

    String destinationName;

    Integer estimatedCost;

    Long likeCount;

    @Column(nullable = false)
    boolean createdByUser;

    Integer createdBy;

    String createdByName;

    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    TravelPlaceCategory category;

    @Getter(AccessLevel.NONE)
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "travelDestination", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    TravelDestinationDetails travelDestinationDetails;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "travelDestinationId", fetch = FetchType.LAZY)
    private List<ImageData> imageDataList;

    @Getter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "travelDestination", fetch = FetchType.LAZY)
    List<TravelDestinationCardIconList> travelDestinationCardIconLists;
}
