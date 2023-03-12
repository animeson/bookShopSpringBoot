package com.svistun.bookshoop.dto;

import com.svistun.bookshoop.entity.BookImage;

import java.util.List;

public record BookDto(
        Long bookID,
        List<AuthorDto> author,
        String publisher,
        Short years,
        Short page,
        String ISBN,
        String binding,
        String format,
        List<String> categories,
        Short weight,
        String infoPublisher,
        List<CommentDto> comments,
        List<ImageBookDTO> images,
        Double rating


) {
}
