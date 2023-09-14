package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.WalletTotalMoneyRequestDTO;
import com.example.travelguidewebapplication.DTO.response.WalletTotalMonetResponseDTO;
import com.example.travelguidewebapplication.exception.DuplicateWalletException;
import com.example.travelguidewebapplication.exception.TotalMoneyLessThanExpenses;
import com.example.travelguidewebapplication.model.Expenses;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.model.Wallet;
import com.example.travelguidewebapplication.repository.ExpensesRepository;
import com.example.travelguidewebapplication.repository.WalletRepository;
import com.example.travelguidewebapplication.service.inter.UserService;
import com.example.travelguidewebapplication.service.inter.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final UserService userService;
    private final ExpensesRepository expensesRepository;

    @Override
    public void addTotalMoney(WalletTotalMoneyRequestDTO walletTotalMoneyRequestDTO) {
        User user = userService.getCurrentUser();

        Wallet existingWallet = walletRepository.findByUser(user);

        if (existingWallet != null) {
            throw new DuplicateWalletException();
        }

        Wallet wallet = Wallet.builder()
                .totalMoney(walletTotalMoneyRequestDTO.getTotalMoney())
                .moneyLeft(walletTotalMoneyRequestDTO.getTotalMoney())
                .user(userService.getCurrentUser())
                .build();
        walletRepository.save(wallet);
    }

    @Override
    public WalletTotalMonetResponseDTO isHaveTotalMoney() {
        User user = userService.getCurrentUser();

        Wallet existingWallet = walletRepository.findByUser(user);

        if (existingWallet != null) {
            return WalletTotalMonetResponseDTO.builder()
                    .isHaveTotalMoney(true)
                    .totalMoney(existingWallet.getTotalMoney())
                    .moneyLeft(existingWallet.getMoneyLeft())
                    .build();
        } else {
            return WalletTotalMonetResponseDTO.builder()
                    .isHaveTotalMoney(false)
                    .totalMoney(0.0)
                    .moneyLeft(0.0)
                    .build();
        }
    }

    @Override
    public void resetUserWallet() {
        User user = userService.getCurrentUser();

        List<Expenses> expensesList = expensesRepository.costListForDelete(user);
        expensesRepository.deleteAll(expensesList);

        Wallet existingWallet = walletRepository.findByUser(user);
        if (existingWallet != null) {
            walletRepository.delete(existingWallet);
        }
    }

    @Override
    public WalletTotalMonetResponseDTO changeTotalMoney(WalletTotalMoneyRequestDTO moneyRequestDTO) {
        User user = userService.getCurrentUser();

        Wallet existingWallet = walletRepository.findByUser(user);

        if (Objects.equals(existingWallet.getTotalMoney(), moneyRequestDTO.getTotalMoney())) {
            return null;
        }

        List<Expenses> expensesList = expensesRepository.costListByUser(user);
        Double totalCost = 0.0;
        for (Expenses expenses : expensesList) {
            totalCost += expenses.getCost();
        }
        if (totalCost > moneyRequestDTO.getTotalMoney()) {
            throw new TotalMoneyLessThanExpenses();
        }

        Double totalMoney = existingWallet.getTotalMoney();
        Double newTotalMoney = moneyRequestDTO.getTotalMoney();
        Double oldMoneyLeft = existingWallet.getMoneyLeft();
        Double newMoneyLeft = 0.0;

        if (totalMoney > newTotalMoney) {
            newMoneyLeft = oldMoneyLeft - (totalMoney - newTotalMoney);
        } else if (totalMoney < newTotalMoney) {
            newMoneyLeft = oldMoneyLeft + (newTotalMoney - totalMoney);
        }
        existingWallet.setTotalMoney(moneyRequestDTO.getTotalMoney());
        existingWallet.setMoneyLeft(newMoneyLeft);
        walletRepository.save(existingWallet);
        return WalletTotalMonetResponseDTO.builder()
                .moneyLeft(newMoneyLeft)
                .totalMoney(newTotalMoney)
                .isHaveTotalMoney(true)
                .build();
    }
}
