package com.example.travelguidewebapplication.error;

import com.example.travelguidewebapplication.exception.NotUniqeUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(value = NotUniqeUser.class)
    public ResponseEntity<Object> userException() {
        return new ResponseEntity<>("This email is not uniqe", HttpStatus.FOUND);
    }
}
