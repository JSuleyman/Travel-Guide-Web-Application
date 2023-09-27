package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.service.inter.SalesReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/sales_receipt")
@RequiredArgsConstructor
public class SalesReceiptController {
    private final SalesReceiptService service;

    @PostMapping("/{expenseId}")
    public void uploadImage(@RequestParam("image") MultipartFile files, @PathVariable String expenseId) throws IOException {
        service.uploadImage(files, expenseId);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<?> downloadImage(@PathVariable String expenseId) {
        byte[] imageData = service.downloadImage(expenseId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpeg"))
                .body(imageData);
    }
}
