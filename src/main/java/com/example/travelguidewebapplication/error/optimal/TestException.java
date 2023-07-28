package com.example.travelguidewebapplication.error.optimal;

public class TestException extends CoreException {
    final static int statusCode = 603;

    public TestException(String message) {
        super(message, statusCode);
    }
}
