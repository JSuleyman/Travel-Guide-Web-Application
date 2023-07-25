package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.UserCommentDTO;
import com.example.travelguidewebapplication.DTO.response.UserCommentBoxResponseDTO;

import java.util.List;

public interface UserCommentService {
    void save(UserCommentDTO userCommentDTO);

    List<UserCommentBoxResponseDTO> getUserCommentListByPlacesId(String id, int first, int offset);

    Integer currentUserId();
}
