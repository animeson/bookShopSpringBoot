package com.svistun.bookshoop.service.catalog;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.dto.CategoryDto;
import com.svistun.bookshoop.service.book.BookServiceImpl;
import com.svistun.bookshoop.service.category.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final BookServiceImpl bookService;
    private final CategoryServiceImpl categoryService;

    @Override
    public Collection<CategoryDto> getCategory() {
        return categoryService.getAllCategory();
    }

    @Override
    public BookDto getBookByBookId(Long bookId) {
        return bookService.getBookByBookId(bookId);
    }

    @Override
    public Page<BookMainPageDto> getBookByCategoryID(Long categoryID, Pageable pageable) {
        return bookService.findByCategoryID(categoryID, pageable);
    }

    @Override
    public Page<BookMainPageDto> getAllBook(Pageable pageable) {
        return bookService.getAllBook(pageable);
    }

    @Override
    public Page<BookMainPageDto> findByAuthorName(String authorName, Pageable pageable) {
        return bookService.findByAuthorName(authorName,pageable);
    }
}
