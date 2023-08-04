package com.example.travelguidewebapplication.util;

import org.apache.commons.lang3.RandomStringUtils;

public class VerificationCodeGenerator {
    public static String generateVerificationCode() {
        return RandomStringUtils.randomAlphabetic(6);
    }
}
