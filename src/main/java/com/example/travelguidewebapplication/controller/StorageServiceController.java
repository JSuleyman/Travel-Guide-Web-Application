package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.service.inter.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@Slf4j
public class StorageServiceController {
    private final StorageService service;

    @PostMapping("/{fkPlacesToVisitId}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile[] files, @PathVariable String fkPlacesToVisitId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.uploadImage(files, fkPlacesToVisitId));
    }

    @GetMapping("/{fkPlacesToVisitId}/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fkPlacesToVisitId, @PathVariable String fileName) {
        log.info(fkPlacesToVisitId,fileName);
        byte[] imageData = service.downloadImage(fileName, fkPlacesToVisitId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpeg"))
                .body(imageData);
    }
}
