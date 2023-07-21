package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.LikeBtnDTO;
import com.example.travelguidewebapplication.model.TravelDestination;

import java.util.List;

public interface LikeBtnService {
    Long add(LikeBtnDTO likeBtnDTO);

    boolean isLike(String id);

    Long delete(LikeBtnDTO likeBtnDTO);

}
