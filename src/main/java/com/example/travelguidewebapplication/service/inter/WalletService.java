package com.example.travelguidewebapplication.service.inter;

import com.example.travelguidewebapplication.DTO.WalletTotalMonetRequestDTO;
import com.example.travelguidewebapplication.DTO.response.WalletTotalMonetResponseDTO;

public interface WalletService {
    void addTotalMoney(WalletTotalMonetRequestDTO walletTotalMonetRequestDTO);

    WalletTotalMonetResponseDTO isHaveTotalMoney();

    void resetUserWallet();
}
