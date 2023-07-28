package com.example.travelguidewebapplication.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class RedisCacheController {

    private int sayac = 0;

    private final RedisCacheService redisCacheService;

    @GetMapping
    public String cacheControl() throws InterruptedException {
        if (sayac == 5){
            redisCacheService.clearCache();
            sayac = 0;
        }
        sayac++;
        return redisCacheService.longRunnigMethod();
    }
}
