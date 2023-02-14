package com.svistun.bookshoop.dto;

import org.springframework.data.domain.Pageable;

import java.util.List;

public record BookMainPageDto(
        Long bookID,
        String name,
        List<AuthorDto> author,
        Short years
) {
}
