package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.ExpensesDateFilterRequestDTO;
import com.example.travelguidewebapplication.DTO.ExpensesRequestDTO;
import com.example.travelguidewebapplication.DTO.response.ExpensesResponseDTO;
import com.example.travelguidewebapplication.DTO.response.MoneyLeftResponseDTO;
import com.example.travelguidewebapplication.exception.MoneyLeftLessThanCost;
import com.example.travelguidewebapplication.exception.TotalMoneyLessThanExpenses;
import com.example.travelguidewebapplication.model.Expenses;
import com.example.travelguidewebapplication.model.SalesReceipt;
import com.example.travelguidewebapplication.model.Wallet;
import com.example.travelguidewebapplication.repository.ExpensesRepository;
import com.example.travelguidewebapplication.repository.SalesReceiptRepository;
import com.example.travelguidewebapplication.repository.WalletRepository;
import com.example.travelguidewebapplication.service.inter.ExpensesService;
import com.example.travelguidewebapplication.service.inter.SalesReceiptService;
import com.example.travelguidewebapplication.service.inter.UserService;
import com.example.travelguidewebapplication.service.inter.WalletService;
import com.example.travelguidewebapplication.util.DateHelper;
import jakarta.transaction.Transactional;
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
    private final SalesReceiptRepository salesReceiptRepository;
//    private final CurrencyRepository currencyRepository;

    @Override
    public MoneyLeftResponseDTO addNewCost(ExpensesRequestDTO expenses) {
//        Currency currency;
//        if (expenses.getCurrencyId() == null || expenses.getCurrencyId().trim().isEmpty()) {
//            currency = currencyRepository.findByCurrency("AZN");
//        } else {
//            currency = currencyRepository.findById(expenses.getCurrencyId()).orElseThrow();
//        }
        Wallet wallet = walletRepository.findByUser(userService.getCurrentUser());

        if (wallet.getTotalMoney() < expenses.getCost()) {
            throw new TotalMoneyLessThanExpenses();
        } else if (wallet.getMoneyLeft() < expenses.getCost()) {
            throw new MoneyLeftLessThanCost();
        }

        Expenses expenses1 = Expenses.builder()
                .cost(expenses.getCost())
                .costDescription(expenses.getCostDescription())
                .userId(userService.getCurrentUser())
//                .currencyId(currency)
                .localDateTime(DateHelper.getAzerbaijanDateTime())
                .status("A")
                .build();
        expensesRepository.save(expenses1);

        double number = wallet.getMoneyLeft() - expenses1.getCost();
        double rounded = Math.round(number * 100.0) / 100.0;
        wallet.setMoneyLeft(rounded);
        walletRepository.save(wallet);

        return MoneyLeftResponseDTO.builder()
                .moneyLeft(wallet.getMoneyLeft())
                .expenseId(expenses1.getId())
                .build();
    }

    @Override
    @Transactional
    public List<ExpensesResponseDTO> costListByUserId() {
        List<Expenses> expensesList = expensesRepository.costListByUser(userService.getCurrentUser());
        List<ExpensesResponseDTO> expensesResponseDTOS = new ArrayList<>();


        for (Expenses expenses : expensesList) {
            SalesReceipt salesReceipt = salesReceiptRepository.findByExpense(expenses);
            boolean isHaveImage;

            if (salesReceipt == null) {
                isHaveImage = false;
            } else {
                isHaveImage = true;
            }

            ExpensesResponseDTO expensesResponseDTO = ExpensesResponseDTO.builder()
                    .cost(expenses.getCost())
                    .costDescription(expenses.getCostDescription())
//                    .currency(expenses.getCurrencyId().getCurrency())
                    .localDateTime(expenses.getLocalDateTime())
                    .id(expenses.getId())
                    .isHaveImage(isHaveImage)
                    .build();
            expensesResponseDTOS.add(expensesResponseDTO);
            System.out.println("isHaveImage: " + isHaveImage);
        }
        return expensesResponseDTOS;
    }

    @Override
    @Transactional
    public MoneyLeftResponseDTO deleteCostById(String id) {
        Expenses expenses = expensesRepository.findByIdAndStatus(id, "A");
        expenses.setStatus("D");
        expensesRepository.save(expenses);

        Wallet wallet = walletRepository.findByUser(userService.getCurrentUser());
        double number = wallet.getMoneyLeft() + expenses.getCost();
        double rounded = Math.round(number * 100.0) / 100.0;
        wallet.setMoneyLeft(rounded);
        walletRepository.save(wallet);

        SalesReceipt salesReceipt = salesReceiptRepository.findByExpense(expenses);
        if (salesReceipt != null) {
            salesReceiptRepository.delete(salesReceipt);
        }


        return MoneyLeftResponseDTO.builder()
                .moneyLeft(wallet.getMoneyLeft())
                .build();
    }

    @Override
    public List<ExpensesResponseDTO> costListByDateFilter(
            ExpensesDateFilterRequestDTO expensesDateFilterRequestDTO) {
        return null;
    }
}
