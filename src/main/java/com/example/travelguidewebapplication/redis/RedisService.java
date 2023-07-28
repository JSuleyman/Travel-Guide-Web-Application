package com.example.travelguidewebapplication.redis;

import com.example.travelguidewebapplication.DTO.response.PlacesToVisitByValueResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    private final RedisTemplate<String, PlacesToVisitByValueResponseDTO> redisTemplate;
    private final ListOperations<String, PlacesToVisitByValueResponseDTO> listOperations;

    @Autowired
    public RedisService(RedisTemplate<String, PlacesToVisitByValueResponseDTO> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.listOperations = redisTemplate.opsForList();
    }

    // Liste ekleme işlemi
    public void addToList(String key, List<PlacesToVisitByValueResponseDTO> values) {
        listOperations.leftPushAll(key, values);
    }

    // Liste çekme işlemi
    public List<PlacesToVisitByValueResponseDTO> getList(String key) {
        return listOperations.range(key, 0, -1);
    }
}
