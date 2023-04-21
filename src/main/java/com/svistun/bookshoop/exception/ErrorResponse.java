package com.svistun.bookshoop.exception;

import lombok.Data;
@Data
public class ErrorResponse {
    private String message;
    private String dateTimeError;
    private String status;
}
