package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.WalletTotalMoneyRequestDTO;
import com.example.travelguidewebapplication.DTO.response.WalletTotalMonetResponseDTO;

public interface WalletService {
    void addTotalMoney(WalletTotalMoneyRequestDTO walletTotalMoneyRequestDTO);

    WalletTotalMonetResponseDTO isHaveTotalMoney();

    void resetUserWallet();

    WalletTotalMonetResponseDTO changeTotalMoney(WalletTotalMoneyRequestDTO monetRequestDTO);

}
