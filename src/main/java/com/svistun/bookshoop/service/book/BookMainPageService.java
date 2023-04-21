package com.svistun.bookshoop.service.book;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookMainPageService {
    Page<BookMainPageDto> findByCategoryID(Long categoryID, Pageable pageable);
    Page<BookMainPageDto> findByAuthorID(Long authorID,Pageable pageable);
    Page<BookMainPageDto> getAllBookMainPage(Pageable pageable);
    BookDto getBookByBookId(Long bookId);

}
