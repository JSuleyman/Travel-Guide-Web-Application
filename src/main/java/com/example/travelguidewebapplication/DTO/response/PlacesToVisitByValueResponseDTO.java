package com.example.travelguidewebapplication.DTO.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PlacesToVisitByValueResponseDTO implements Serializable {
    String id;
    String destination;
    String imageUrl;
    Integer estimatedCost;
    Long likeCount;
    String createdByName;
}
