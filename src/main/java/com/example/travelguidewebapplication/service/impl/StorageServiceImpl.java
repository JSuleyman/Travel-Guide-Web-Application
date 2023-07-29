package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.model.ImageData;
import com.example.travelguidewebapplication.model.TravelDestination;
import com.example.travelguidewebapplication.repository.StorageRepository;
import com.example.travelguidewebapplication.repository.TravelDestinationRepository;
import com.example.travelguidewebapplication.service.inter.StorageService;
import com.example.travelguidewebapplication.util.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageServiceImpl implements StorageService {

    private final StorageRepository repository;
    private final TravelDestinationRepository travelDestinationRepository;

    private static final String REDIS_KEY_PREFIX = "image_data_";
    private final RedisTemplate<String, byte[]> redisTemplate;
    private final RedisConnectionFactory redisConnectionFactory;

    @Override
    public String uploadImage(MultipartFile[] file, String travelDestinationId) throws IOException {
        TravelDestination travelDestination = travelDestinationRepository.findById(travelDestinationId).orElseThrow();
        for (MultipartFile file1 : file) {
            repository.save(ImageData.builder()
                    .name(file1.getOriginalFilename())
                    .travelDestinationId(travelDestination)
                    .type(file1.getContentType())
                    .imageData(ImageUtils.compressImage(file1.getBytes())).build());
        }
        return null;
    }

    @Transactional
    public byte[] downloadImage(String fileName, String travelDestinationId) {
        String redisKey = REDIS_KEY_PREFIX + fileName + travelDestinationId;
        byte[] responseDTOs = redisTemplate.opsForValue().get(redisKey);
        if (responseDTOs == null) {
            Optional<ImageData> dbImageData = repository.findByNameAndAndTravelDestinationId_Id(fileName, travelDestinationId);
            byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
            try (RedisConnection connection = redisConnectionFactory.getConnection()) {
                redisTemplate.opsForValue().set(redisKey, images);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return images;
        }
        return responseDTOs;
    }
}
