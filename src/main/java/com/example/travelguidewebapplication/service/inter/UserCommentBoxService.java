package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.UserCommentBoxDTO;
import com.example.travelguidewebapplication.DTO.response.UserCommentBoxResponseDTO;

import java.util.List;

public interface UserCommentBoxService {
    void save(UserCommentBoxDTO userCommentBoxDTO);

    List<UserCommentBoxResponseDTO> getUserCommentListByPlacesId(String id);

    Integer currentUserId();
}
