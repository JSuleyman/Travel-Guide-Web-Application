package com.example.travelguidewebapplication.DTO;

import lombok.Data;

@Data
public class UserCustomCardRequestDTO {
    String keyId;

    String places;

    Integer manyForTravel;

    String imageUrl;

    String userComments;

    String events;

}
