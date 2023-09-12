package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.ExpensesRequestDTO;
import com.example.travelguidewebapplication.DTO.response.ExpensesResponseDTO;
import com.example.travelguidewebapplication.DTO.response.MoneyLeftResponseDTO;
import com.example.travelguidewebapplication.model.Expenses;
import com.example.travelguidewebapplication.model.Wallet;
import com.example.travelguidewebapplication.repository.ExpensesRepository;
import com.example.travelguidewebapplication.repository.WalletRepository;
import com.example.travelguidewebapplication.service.inter.ExpensesService;
import com.example.travelguidewebapplication.service.inter.UserService;
import com.example.travelguidewebapplication.service.inter.WalletService;
import com.example.travelguidewebapplication.util.DateHelper;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.descriptor.java.DataHelper;
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
//    private final CurrencyRepository currencyRepository;

    @Override
    public MoneyLeftResponseDTO addNewCost(ExpensesRequestDTO expenses) {
//        Currency currency;
//        if (expenses.getCurrencyId() == null || expenses.getCurrencyId().trim().isEmpty()) {
//            currency = currencyRepository.findByCurrency("AZN");
//        } else {
//            currency = currencyRepository.findById(expenses.getCurrencyId()).orElseThrow();
//        }
        Expenses expenses1 = Expenses.builder()
                .cost(expenses.getCost())
                .costDescription(expenses.getCostDescription())
                .userId(userService.getCurrentUser())
//                .currencyId(currency)
                .localDateTime(DateHelper.getAzerbaijanDateTime())
                .status("A")
                .build();
        expensesRepository.save(expenses1);

        Wallet wallet = walletRepository.findByUser(userService.getCurrentUser());

        double number = wallet.getMoneyLeft() - expenses1.getCost();
        double rounded = Math.round(number * 100.0) / 100.0;
        wallet.setMoneyLeft(rounded);
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
//                    .currency(expenses.getCurrencyId().getCurrency())
                    .localDateTime(expenses.getLocalDateTime())
                    .id(expenses.getId())
                    .build();
            expensesResponseDTOS.add(expensesResponseDTO);
        }
        return expensesResponseDTOS;
    }

    @Override
    public MoneyLeftResponseDTO deleteCostById(String id) {
        Expenses expenses = expensesRepository.findByIdAndStatus(id, "A");
        expenses.setStatus("D");
        expensesRepository.save(expenses);

        Wallet wallet = walletRepository.findByUser(userService.getCurrentUser());
        double number = wallet.getMoneyLeft() + expenses.getCost();
        double rounded = Math.round(number * 100.0) / 100.0;
        wallet.setMoneyLeft(rounded);
        walletRepository.save(wallet);

        return MoneyLeftResponseDTO.builder()
                .moneyLeft(wallet.getMoneyLeft())
                .build();
    }
}
