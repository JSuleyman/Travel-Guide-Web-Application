package com.example.travelguidewebapplication.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserCustomCardRequestDTO {
    String categoryId;

    String destinationName;

    Integer estimatedCost;

    String imageUrl;

    String userComments;

    String events;

    List<String> selectedIcons;

}
