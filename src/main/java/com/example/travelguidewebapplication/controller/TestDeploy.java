package com.example.travelguidewebapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestDeploy {

    @GetMapping("/deploy")
    public String testDeploy(){
        return "Test Deploy";
    }
}
