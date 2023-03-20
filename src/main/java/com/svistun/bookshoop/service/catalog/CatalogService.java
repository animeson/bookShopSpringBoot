package com.svistun.bookshoop.service.catalog;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CatalogService {
    Collection<CategoryDto> getCategory();
    BookDto getBookByBookId(Long bookId);
    Page<BookMainPageDto> getBookByCategoryID(Long categoryID, Pageable pageable);
    Page<BookMainPageDto> getAllBook(Pageable pageable);
    Page<BookMainPageDto> findByAuthorName(String authorName,Pageable pageable);
}
