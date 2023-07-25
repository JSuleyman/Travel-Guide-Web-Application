package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.UserFullNameUpdateRequestDTO;
import com.example.travelguidewebapplication.DTO.response.UserFullNameUpdateResponseDTO;
import com.example.travelguidewebapplication.exception.NotFoundUser;
import com.example.travelguidewebapplication.model.SessionManager;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.UserRespository;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRespository userRespository;
    private final SessionManager sessionManager;

    @Override
    public User getCurrentUser() {
        return userRespository.findByEmail(sessionManager.getUserName()).stream()
                .findFirst()
                .orElseThrow(NotFoundUser::new);
    }

    @Override
    public UserFullNameUpdateResponseDTO updateUserFullName(UserFullNameUpdateRequestDTO userFullNameUpdateRequestDTO) {
        User user = getCurrentUser();

        user.setFirstname(userFullNameUpdateRequestDTO.getFirstName());
        user.setLastname(userFullNameUpdateRequestDTO.getLastName());
        userRespository.save(user);

        return UserFullNameUpdateResponseDTO.builder()
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .build();
    }
}
