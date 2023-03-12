package com.svistun.bookshoop.dto;

public record UserCommentDto(
        Long userID,
        String lastName,
        String firstName
) {
}
