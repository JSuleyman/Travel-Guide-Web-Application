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
    public ResponseEntity<ErrorResponse> handleNotUniqueUserException(NotUniqueUser ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage("This email is already registered.");
        errorResponse.setStatus(HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(value = WrongPassword.class)
    public ResponseEntity<ErrorResponse> handleWrongPasswordException(WrongPassword ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage("Wrong password.");
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(value = NotFoundUser.class)
    public ResponseEntity<ErrorResponse> handleNotFoundUserException(NotFoundUser ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage("No such email address was found.");
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = EmptyMessageException.class)
    public ResponseEntity<ErrorResponse> handleEmptyMessageException(EmptyMessageException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage("Message box cannot be empty.");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}


