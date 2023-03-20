package com.svistun.bookshoop.service.book;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    Page<BookMainPageDto> getAllBook(Pageable pageable);
    BookDto getBookByBookId(Long bookId);
    Book getBookId (Long bookId);
    Page<BookMainPageDto> getAllBookMainPage(Page<Book> book, Pageable pageable);
    Page<BookMainPageDto> findByCategoryID(Long categoryID,Pageable pageable);
    Page<BookMainPageDto> findByAuthorName(String authorName,Pageable pageable);



}
