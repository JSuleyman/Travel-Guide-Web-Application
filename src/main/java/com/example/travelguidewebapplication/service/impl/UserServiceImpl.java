package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.repository.UserRespository;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRespository userRespository;

//    public UserDetails findUserByEmail(String emil){
//        List<User> users = userRespository.findAll();
//
//        return users.
//    }
}
