package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.UserCustomCardRequestDTO;
import com.example.travelguidewebapplication.model.PlacesToVisit;

import java.util.List;

public interface PlacesToVisitService {
    List<PlacesToVisit> getByValue(String key);

    void userCustomCard(UserCustomCardRequestDTO userCustomCardRequestDTO);
}
