package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.Expenses;
import com.example.travelguidewebapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, String> {
    @Query("""
            SELECT e from Expenses e where e.userId = :userId
            """)
    List<Expenses> costListByUser(User userId);
}
