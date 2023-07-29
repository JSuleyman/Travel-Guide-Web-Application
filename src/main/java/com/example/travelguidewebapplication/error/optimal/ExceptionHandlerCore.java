package com.example.travelguidewebapplication.error.optimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

//@ControllerAdvice
public class ExceptionHandlerCore extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
//        return buildResponseEntity(ex, request);
//    }

    //Helper Methods

    private ResponseEntity<Object> buildResponseEntity(Exception ex, WebRequest request) {
        String message = "Unexpected Exception";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int statusCode = status.value();

        if (ex instanceof CoreException) {
            message = ex.getMessage();
            statusCode = ((CoreException) ex).getStatusCode();
        }

        return getResponseEntity(ex, status, request, message, statusCode);
    }

    private ResponseEntity<Object> getResponseEntity(Exception ex,
                                                     HttpStatus status,
                                                     WebRequest request,
                                                     String message, int statusCode) {
        ResponseException responseException = ResponseException.builder()
                .status(statusCode)
                .error(status.getReasonPhrase())
                .message(message)
                .errorDetail(ex.getMessage())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();


        return new ResponseEntity<>(responseException, status);
    }
}