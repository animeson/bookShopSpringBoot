package com.svistun.bookshoop.dto;

public record AuthorDto(
        Long authorID,
        String lastName,
        String firstName
) {
}
