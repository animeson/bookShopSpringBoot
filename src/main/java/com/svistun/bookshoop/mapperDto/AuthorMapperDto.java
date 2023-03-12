package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.AuthorDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.entity.Author;
import com.svistun.bookshoop.entity.Book;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AuthorMapperDto implements Function<Author, AuthorDto> {
    @Override
    public AuthorDto apply(Author author) {
        return new AuthorDto(
                author.getAuthorID(),
                author.getLastName(),
                author.getFirstName());
    }
}
