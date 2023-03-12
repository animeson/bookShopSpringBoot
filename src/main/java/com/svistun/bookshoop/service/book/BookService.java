package com.svistun.bookshoop.service.book;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.Collection;
import java.util.List;
import java.util.LongSummaryStatistics;

@Service
public interface BookService {
    Page<BookMainPageDto> getAllBook(Pageable pageable);
    BookDto getBookByBookId(Long bookId);
    Page<BookMainPageDto> getAllBookMainPage(Page<Book> book, Pageable pageable);
    Page<BookMainPageDto> findByCategoryName(String categoryName,Pageable pageable);
    Page<BookMainPageDto> findByAuthorName(String authorName,Pageable pageable);



}
