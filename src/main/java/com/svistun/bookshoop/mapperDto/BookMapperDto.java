package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.*;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookMapperDto implements Function<Book, BookDto> {
    private final AuthorMapperDto authorMapperDto;
    private final ImageMapperDto imageMapperDto;
    private final CommentMapperDto commentMapperDto;

    @Override
    public BookDto apply(Book book) {
        return new BookDto(
                book.getBookID(),
                book.getName(),
                book.getAboutBook(),
                book.getAuthor()
                        .stream()
                        .map(authorMapperDto)
                        .collect(Collectors.toList()),
                book.getPublisher(),
                book.getYears(),
                book.getPage(),
                book.getISBN(),
                book.getBinding(),
                book.getFormat(),
                book.getCategories()
                        .stream()
                        .map(Category::getName)
                        .collect(Collectors.toList()),
                book.getWeight(),
                book.getInfoPublisher(),
                book.getComments()
                        .stream()
                        .map(commentMapperDto)
                        .collect(Collectors.toList()),
                book.getImages()
                        .stream()
                        .map(imageMapperDto)
                        .collect(Collectors.toList()),
                book.getRating().getRating()
        );
    }
}

