package com.svistun.bookshoop.controller.book;

import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.service.book.BookService;
import com.svistun.bookshoop.service.book.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.SortComparator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/book")
@Log4j2
public class BookController {
    private final BookServiceImpl bookService;

    @GetMapping
    public ResponseEntity<Page<Book>> findAllBook(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "2") int size) {
        Page<Book> books = bookService.findAllBook(PageRequest.of(page, size, Sort.by("BookID")));
        return ResponseEntity.ok(books);
    }






}
