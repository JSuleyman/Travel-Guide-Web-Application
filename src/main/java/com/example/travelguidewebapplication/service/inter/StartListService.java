package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.StarRequestDTO;
import com.example.travelguidewebapplication.model.TravelDestination;
import com.example.travelguidewebapplication.model.SessionManager;

import java.util.List;

public interface StartListService {
    void add(StarRequestDTO starRequestDTO);

    boolean isFavorite(String id);

    void delete(StarRequestDTO starRequestDTO);

    SessionManager profilDeyisdirmelidir();

    List<TravelDestination> getAll();
}
