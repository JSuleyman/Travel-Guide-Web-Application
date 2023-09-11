package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.ExpensesRequestDTO;
import com.example.travelguidewebapplication.DTO.WalletTotalMonetRequestDTO;
import com.example.travelguidewebapplication.DTO.response.ExpensesResponseDTO;
import com.example.travelguidewebapplication.DTO.response.MoneyLeftResponseDTO;
import com.example.travelguidewebapplication.DTO.response.WalletTotalMonetResponseDTO;
import com.example.travelguidewebapplication.service.inter.ExpensesService;
import com.example.travelguidewebapplication.service.inter.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet_management")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    private final ExpensesService expensesService;
//    private final CurrencyService currencyService;

    @PostMapping("/total_money")
    public void addTotalMoney(@RequestBody WalletTotalMonetRequestDTO walletTotalMonetRequestDTO) {
        walletService.addTotalMoney(walletTotalMonetRequestDTO);
    }

    @GetMapping("/is_have_total_money")
    public ResponseEntity<WalletTotalMonetResponseDTO> isHaveTotalMoney() {
        return ResponseEntity.ok(walletService.isHaveTotalMoney());
    }

    @DeleteMapping("/reset_wallet")
    public void resetUserWallet() {
        walletService.resetUserWallet();
    }

    //    Expenses Methods
    @PostMapping("/cost")
    public ResponseEntity<MoneyLeftResponseDTO> addNewCost(@RequestBody ExpensesRequestDTO expenses) {
        System.out.println(expenses);
        return ResponseEntity.ok(expensesService.addNewCost(expenses));
    }

    @GetMapping("/cost_list")
    public ResponseEntity<List<ExpensesResponseDTO>> costListByUser() {
        return ResponseEntity.ok(expensesService.costListByUserId());
    }

    @PostMapping("/delete_cost")
    public ResponseEntity<MoneyLeftResponseDTO> deleteCostById(@RequestParam String id) {
        return ResponseEntity.ok(expensesService.deleteCostById(id));
    }

//    //    Currencies
//    @GetMapping("/currencies")
//    public ResponseEntity<List<Currency>> getAll() {
//        return ResponseEntity.ok(currencyService.getAll());
//    }
}
