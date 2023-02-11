package com.svistun.bookshoop.service.category;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogService {
    Object getMainPageCatalog();
    BookDto getBookByBookId(Long bookId);
    List<BookMainPageDto> getBookByCategoryName(String categoryName);
}
