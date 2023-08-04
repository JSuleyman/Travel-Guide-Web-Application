package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query("""
            select u from User u where u.email = :email and u.verified = false
            """)
    User findByEmailForVerify(String email);
}
