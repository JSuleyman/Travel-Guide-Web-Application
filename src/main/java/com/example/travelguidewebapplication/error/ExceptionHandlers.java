package com.example.travelguidewebapplication.error;

import com.example.travelguidewebapplication.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

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

    @ExceptionHandler(value = MandatoryException.class)
    public ResponseEntity<ErrorResponse> handleMandatoryMessageException(MandatoryException ex) {
        List<String> missingFields = ex.getMissingFields();
        String errorMessage = String.format("%s %s mandatory.", String.join(", ", missingFields), missingFields.size() > 1 ? "are" : "is");

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(errorMessage);
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatch(PasswordMismatchException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage("The new passwords do not match!");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(value = IsNotVerifiredUser.class)
    public ResponseEntity<ErrorResponse> handleIsNotVerifiedUser(IsNotVerifiredUser ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage("This email is not verified!");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(value = VerificationCodeHasExpired.class)
    public ResponseEntity<ErrorResponse> handleVerificationCodeHasExpired(VerificationCodeHasExpired ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage("Verification code has expired. Please request a new code.");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(value = InvalidVerificationCode.class)
    public ResponseEntity<ErrorResponse> handleInvalidVerificationCode(InvalidVerificationCode ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage("Invalid verification code!");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }
}


