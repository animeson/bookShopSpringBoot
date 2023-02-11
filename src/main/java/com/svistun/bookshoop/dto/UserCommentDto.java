package com.svistun.bookshoop.dto;

public record UserCommentDto(
        Long personID,
        String lastName,
        String firstName
) {
}
