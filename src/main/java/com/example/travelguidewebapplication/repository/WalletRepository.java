package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {

    Wallet findByUser(User user);
}
