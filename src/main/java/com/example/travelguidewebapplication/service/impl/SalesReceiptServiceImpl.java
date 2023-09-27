package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.model.Expenses;
import com.example.travelguidewebapplication.model.ImageData;
import com.example.travelguidewebapplication.model.SalesReceipt;
import com.example.travelguidewebapplication.model.TravelDestination;
import com.example.travelguidewebapplication.repository.ExpensesRepository;
import com.example.travelguidewebapplication.repository.SalesReceiptRepository;
import com.example.travelguidewebapplication.service.inter.SalesReceiptService;
import com.example.travelguidewebapplication.util.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalesReceiptServiceImpl implements SalesReceiptService {
    private final SalesReceiptRepository salesReceiptRepository;
    private final ExpensesRepository expensesRepository;

    @Override
    public void uploadImage(MultipartFile file, String expenseId) throws IOException {
        Expenses expenses = expensesRepository.findById(expenseId).orElseThrow();

        salesReceiptRepository.save(SalesReceipt.builder()
                .name(file.getOriginalFilename())
                .expense(expenses)
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
    }

    @Transactional
    public byte[] downloadImage(String expenseId) {
        Expenses expense = expensesRepository.findById(expenseId).orElseThrow();

        SalesReceipt salesReceipt = salesReceiptRepository.findByExpense(expense);

        return ImageUtils.decompressImage(salesReceipt.getImageData());
    }
}
