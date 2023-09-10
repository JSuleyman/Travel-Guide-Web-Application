package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.ExpensesRequestDTO;
import com.example.travelguidewebapplication.DTO.response.ExpensesResponseDTO;
import com.example.travelguidewebapplication.DTO.response.MoneyLeftResponseDTO;
import com.example.travelguidewebapplication.DTO.response.WalletTotalMonetResponseDTO;
import com.example.travelguidewebapplication.model.Expenses;
import com.example.travelguidewebapplication.model.Wallet;
import com.example.travelguidewebapplication.repository.ExpensesRepository;
import com.example.travelguidewebapplication.repository.WalletRepository;
import com.example.travelguidewebapplication.service.inter.ExpensesService;
import com.example.travelguidewebapplication.service.inter.UserService;
import com.example.travelguidewebapplication.service.inter.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {
    private final ExpensesRepository expensesRepository;
    private final WalletService walletService;
    private final UserService userService;
    private final WalletRepository walletRepository;

    @Override
    public MoneyLeftResponseDTO addNewCost(ExpensesRequestDTO expenses) {
        Expenses expenses1 = Expenses.builder()
                .cost(expenses.getCost())
                .costDescription(expenses.getCostDescription())
                .userId(userService.getCurrentUser())
                .build();
        expensesRepository.save(expenses1);

        Wallet wallet = walletRepository.findByUser(userService.getCurrentUser());
        wallet.setMoneyLeft(wallet.getMoneyLeft() - expenses1.getCost());

        walletRepository.save(wallet);

        return MoneyLeftResponseDTO.builder()
                .moneyLeft(wallet.getMoneyLeft())
                .build();
    }

    @Override
    public List<ExpensesResponseDTO> costListByUserId() {
        List<Expenses> expensesList = expensesRepository.costListByUser(userService.getCurrentUser());
        List<ExpensesResponseDTO> expensesResponseDTOS = new ArrayList<>();

        for (Expenses expenses : expensesList) {
            ExpensesResponseDTO expensesResponseDTO = ExpensesResponseDTO.builder()
                    .cost(expenses.getCost())
                    .costDescription(expenses.getCostDescription())
                    .build();
            expensesResponseDTOS.add(expensesResponseDTO);
        }
        return expensesResponseDTOS;
    }
}
