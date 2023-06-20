package com.example.travelguidewebapplication.DTO.response;

import com.example.travelguidewebapplication.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PlacesToVisitDetailsResponseDTO {
    String id;

    List<String> imageUrls;

    Status status;

    String userComments;

    String events;

    String travelDestinationId;
}
