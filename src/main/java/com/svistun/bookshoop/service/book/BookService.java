package com.svistun.bookshoop.service.book;

import com.svistun.bookshoop.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Page<Book> findAllBook(Pageable pageable);
}
