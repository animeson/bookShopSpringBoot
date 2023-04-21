package com.svistun.bookshoop.dto;

public record UserAccountDto(
        Long userID,
        String email,
        String lastName,
        String firstName,
        Boolean gender,
        String phone,
        Integer zipCode,
        String location,
        ImageDTO image
) {
}
