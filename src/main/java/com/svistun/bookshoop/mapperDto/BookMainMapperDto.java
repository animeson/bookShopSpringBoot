package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.AuthorDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.dto.ImageBookDTO;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.BookImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookMainMapperDto implements Function<Book, BookMainPageDto> {
    private final AuthorMapperDto authorMapperDto;
    private final ImageMapperDto imageMapperDto;
    @Override
    public BookMainPageDto apply(Book book) {
        return new BookMainPageDto(
                book.getBookID(),
                book.getName(),
                book.getAuthor()
                        .stream()
                        .map(authorMapperDto).collect(Collectors.toList()),
                book.getYears(),
                book.getImages()
                        .stream()
                        .filter(BookImage::getIsPrimary)
                        .map(imageMapperDto)
                        .findFirst()
                        .orElse(null)
        );
    }
}
