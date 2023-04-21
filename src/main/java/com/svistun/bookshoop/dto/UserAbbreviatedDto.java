package com.svistun.bookshoop.dto;

public record UserAbbreviatedDto(
        Long userID,
        String lastName,
        String firstName,
        ImageDTO image,
        Boolean isVerified
) {
}
