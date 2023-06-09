package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.LikeBtnDTO;
import com.example.travelguidewebapplication.model.PlacesToVisit;

import java.util.List;

public interface LikeBtnService {
    Long add(LikeBtnDTO likeBtnDTO);

    boolean isLike(String id);

    Long delete(LikeBtnDTO likeBtnDTO);

    List<PlacesToVisit> getAll();
}
