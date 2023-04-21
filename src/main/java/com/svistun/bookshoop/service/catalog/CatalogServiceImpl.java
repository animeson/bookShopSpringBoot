package com.svistun.bookshoop.service.catalog;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.dto.CategoryDto;
import com.svistun.bookshoop.service.book.BookMainPageService;
import com.svistun.bookshoop.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements BookCatalogService {
    private final CategoryService categoryService;
    private final BookMainPageService bookMainPageService;

    @Override
    public Page<BookMainPageDto> findByCategoryID(Long categoryID, Pageable pageable) {
        return bookMainPageService.findByCategoryID(categoryID, pageable);
    }

    @Override
    public Page<BookMainPageDto> findByAuthorID(Long authorID, Pageable pageable) {
        return bookMainPageService.findByAuthorID(authorID, pageable);
    }

    @Override
    public Page<BookMainPageDto> getAllBookMainPage(Pageable pageable) {
        return bookMainPageService.getAllBookMainPage(pageable);
    }

    @Override
    public BookDto getBookByBookId(Long bookId) {
        return bookMainPageService.getBookByBookId(bookId);
    }

    @Override
    public Collection<CategoryDto> getAllCategory() {
        return categoryService.getAllCategory();
    }
}

