package com.svistun.bookshoop.service.catalog;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.response.MainPageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogService {
    MainPageResponse getMainPageCatalog(Pageable pageable);
    BookDto getBookByBookId(Long bookId);
    Page<BookMainPageDto> getBookByCategoryName(String categoryName,Pageable pageable);
}
