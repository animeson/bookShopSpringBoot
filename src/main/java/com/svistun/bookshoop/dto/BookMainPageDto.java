package com.svistun.bookshoop.dto;

import com.svistun.bookshoop.entity.BookImage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public record BookMainPageDto(
        Long bookID,
        String name,
        List<AuthorDto> author,
        Short years,
        ImageBookDTO image
) {
}
