package com.example.travelguidewebapplication.error;

import com.example.travelguidewebapplication.exception.EmptyMessageException;
import com.example.travelguidewebapplication.exception.NotFoundUser;
import com.example.travelguidewebapplication.exception.NotUniqueUser;
import com.example.travelguidewebapplication.exception.WrongPassword;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(value = NotUniqueUser.class)
    public ResponseEntity<Object> userException() {
        return new ResponseEntity<>("This email is already", HttpStatus.FOUND);
    }

    @ExceptionHandler(value = WrongPassword.class)
    public ResponseEntity<Object> passwordException() {
        return new ResponseEntity<>("Wrong password", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = NotFoundUser.class)
    public ResponseEntity<Object> notFoundUser() {
        return new ResponseEntity<>("No such e-mail address was found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmptyMessageException.class)
    public ResponseEntity<Object> handleEmptyMessageException() {
        return new ResponseEntity<>("Mesaj qutusu boş qoyula bilməz!", HttpStatus.BAD_REQUEST);
    }
}
