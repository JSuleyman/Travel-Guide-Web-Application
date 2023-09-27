package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.ExpensesDateFilterRequestDTO;
import com.example.travelguidewebapplication.DTO.ExpensesRequestDTO;
import com.example.travelguidewebapplication.DTO.response.ExpensesResponseDTO;
import com.example.travelguidewebapplication.DTO.response.MoneyLeftResponseDTO;
import com.example.travelguidewebapplication.model.Expenses;

import java.util.List;

public interface ExpensesService {
    MoneyLeftResponseDTO addNewCost(ExpensesRequestDTO expenses);

    List<ExpensesResponseDTO> costListByUserId();

    MoneyLeftResponseDTO deleteCostById(String id);

    List<ExpensesResponseDTO> costListByDateFilter(ExpensesDateFilterRequestDTO expensesDateFilterRequestDTO);
}
