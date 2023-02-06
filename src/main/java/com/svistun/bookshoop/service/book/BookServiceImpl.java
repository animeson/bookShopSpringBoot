package com.svistun.bookshoop.service.book;

import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    @Override
    public Page<Book> findAllBook(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
