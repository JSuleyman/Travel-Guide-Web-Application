package com.example.travelguidewebapplication.service.inter;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SalesReceiptService {
    void uploadImage(MultipartFile file, String expenseId) throws IOException;

    public byte[] downloadImage(String expenseId);
}
