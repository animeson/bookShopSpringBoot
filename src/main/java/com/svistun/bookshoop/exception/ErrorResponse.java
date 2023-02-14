package com.svistun.bookshoop.exception;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorResponse {
    private String message;
    private LocalDateTime dateTimeError;
    private String status;
}
