package com.svistun.bookshoop.dto;

public record ImageBookDTO(
        String filename,
        String path,
        Boolean isPrimary,
        String type
) {
}
