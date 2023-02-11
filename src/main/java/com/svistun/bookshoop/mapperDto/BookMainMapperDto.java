package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.AuthorDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.entity.Book;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookMainMapperDto implements Function<Book, BookMainPageDto> {
    @Override
    public BookMainPageDto apply(Book book) {
        return new BookMainPageDto(
                book.getBookID(),
                book.getName(),
                book.getAuthor().stream().map(
                        author -> new AuthorDto(
                                author.getAuthorID(),
                                author.getLastName(),
                                author.getFirstName()
                        )
                ).collect(Collectors.toList()),
                book.getYears()
        );
    }
}
