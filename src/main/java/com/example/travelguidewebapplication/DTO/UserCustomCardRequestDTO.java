package com.example.travelguidewebapplication.DTO;

import lombok.Data;

@Data
public class UserCustomCardRequestDTO {
    String categoryId;

    String destinationName;

    Integer estimatedCost;

    String imageUrl;

    String userComments;

    String events;

}
