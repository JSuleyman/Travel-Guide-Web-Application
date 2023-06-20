package com.example.travelguidewebapplication.service.inter;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
     String uploadImage(MultipartFile[] file, String fkPlacesToVisitId) throws IOException;

     public byte[] downloadImage(String fileName, String fkPlacesToVisitId);
}
