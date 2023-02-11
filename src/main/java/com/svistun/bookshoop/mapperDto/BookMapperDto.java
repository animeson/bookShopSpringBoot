package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.AuthorDto;
import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.CommentDto;
import com.svistun.bookshoop.dto.UserCommentDto;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookMapperDto implements Function<Book, BookDto> {
    @Override
    public BookDto apply(Book book) {
        return new BookDto(
                book.getBookID(),
                book.getAuthor().stream().map(author ->
                                new AuthorDto(
                                        author.getAuthorID(),
                                        author.getLastName(),
                                        author.getFirstName()))
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
                        .map(comment -> new CommentDto(
                                comment.getCommentID(),
                                comment.getComment(),
                                new UserCommentDto(
                                        comment.getUser().getPersonID(),
                                        comment.getUser().getLastName(),
                                        comment.getUser().getFirstName()
                                ),
                                comment.getDateTimeComment()))
                        .collect(Collectors.toList()));
    }
}

