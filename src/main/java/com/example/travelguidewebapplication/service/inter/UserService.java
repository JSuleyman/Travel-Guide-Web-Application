package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.UserFullNameUpdateRequestDTO;
import com.example.travelguidewebapplication.DTO.response.UserFullNameUpdateResponseDTO;
import com.example.travelguidewebapplication.model.User;

public interface UserService {
    User getCurrentUser();

    UserFullNameUpdateResponseDTO updateUserFullName(UserFullNameUpdateRequestDTO userFullNameUpdateRequestDTO);
}
