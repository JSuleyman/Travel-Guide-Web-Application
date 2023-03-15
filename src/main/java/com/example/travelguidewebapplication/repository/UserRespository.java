package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
