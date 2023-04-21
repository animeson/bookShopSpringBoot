package com.svistun.bookshoop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@ControllerAdvice
public class CustomErrorController extends ResponseEntityExceptionHandler {
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy : hh.mm a");

    @ExceptionHandler({NullPointerException.class, InvalidDataException.class})
    public ResponseEntity<Object> handleNotFoundExceptions(Exception ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setDateTimeError(LocalDateTime.now().format(dateFormat));
        errorResponse.setStatus(status.name());
        return new ResponseEntity<>(errorResponse, status);
    }
}

