package com.svistun.bookshoop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomErrorController {
    @ExceptionHandler({NullPointerException.class, InvalidDataException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDateTimeError(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
