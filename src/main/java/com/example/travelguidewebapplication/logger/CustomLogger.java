package com.example.travelguidewebapplication.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomLogger {
    private static final Logger logger = LoggerFactory.getLogger(CustomLogger.class);

    public void logRequest(String logMessage) {
        logger.info(logMessage);
    }
}
