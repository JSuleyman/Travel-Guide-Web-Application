package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.Expenses;
import com.example.travelguidewebapplication.model.SalesReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesReceiptRepository extends JpaRepository<SalesReceipt, String> {
    SalesReceipt findByExpense(Expenses expense);
}
