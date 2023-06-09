package com.example.travelguidewebapplication.service.impl;

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
    public User getUserByUserName() {
        return userRespository.findByEmail(sessionManager.getUserName()).stream()
                .findFirst()
                .orElseThrow(NotFoundUser::new);
    }

}
