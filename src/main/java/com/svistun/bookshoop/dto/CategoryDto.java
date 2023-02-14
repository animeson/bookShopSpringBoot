package com.svistun.bookshoop.dto;

public record CategoryDto(
        Long categoryID,
        String name,
        Integer quantity
) {
}
