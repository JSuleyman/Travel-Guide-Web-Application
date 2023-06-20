package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.TravelDestinationStatusCountRequestDTO;
import com.example.travelguidewebapplication.DTO.UserCustomCardRequestDTO;
import com.example.travelguidewebapplication.DTO.response.UserCreatedListResponseDTO;
import com.example.travelguidewebapplication.DTO.response.PlacesToVisitByValueResponseDTO;

import java.util.List;

public interface TravelDestinationService {
    List<PlacesToVisitByValueResponseDTO> getByValue(String key);

    List<UserCreatedListResponseDTO> createdByUserList(String status);

    String userCustomCard(UserCustomCardRequestDTO userCustomCardRequestDTO) throws IllegalAccessException;

    TravelDestinationStatusCountRequestDTO statusCount();

    void deleteById(String id);
}
