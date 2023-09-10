package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.WalletTotalMonetRequestDTO;
import com.example.travelguidewebapplication.DTO.response.WalletTotalMonetResponseDTO;
import com.example.travelguidewebapplication.exception.DuplicateWalletException;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.model.Wallet;
import com.example.travelguidewebapplication.repository.WalletRepository;
import com.example.travelguidewebapplication.service.inter.UserService;
import com.example.travelguidewebapplication.service.inter.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final UserService userService;

    @Override
    public void addTotalMoney(WalletTotalMonetRequestDTO walletTotalMonetRequestDTO) {
        User user = userService.getCurrentUser();

        Wallet existingWallet = walletRepository.findByUser(user);

        if (existingWallet != null) {
            throw new DuplicateWalletException();
        }

        Wallet wallet = Wallet.builder()
                .totalMoney(walletTotalMonetRequestDTO.getTotalMoney())
                .moneyLeft(walletTotalMonetRequestDTO.getTotalMoney())
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
}
