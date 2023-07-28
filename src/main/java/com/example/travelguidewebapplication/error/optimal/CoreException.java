package com.example.travelguidewebapplication.error.optimal;

public class CoreException extends RuntimeException {
    int statusCode = 0;

    public CoreException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

