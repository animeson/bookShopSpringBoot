package com.svistun.bookshoop.service.catalog;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.response.MainPageResponse;
import com.svistun.bookshoop.service.book.BookServiceImpl;
import com.svistun.bookshoop.service.category.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final BookServiceImpl bookService;
    private final CategoryServiceImpl categoryService;


    @Override
    public MainPageResponse getMainPageCatalog(Pageable pageable) {
        return new MainPageResponse()
                .setBooks(bookService.getAllBook(pageable))
                .setCategory(categoryService.getAllCategory());
    }

    @Override
    public MainPageResponse getMainPageCatalog(String authorName, Pageable pageable) {
        return new MainPageResponse()
                .setBooks(bookService.findByAuthorName(authorName,pageable))
                .setCategory(categoryService.getAllCategory());
    }

    @Override
    public BookDto getBookByBookId(Long bookId) {
        return bookService.getBookByBookId(bookId);
    }

    @Override
    public Page<BookMainPageDto> getBookByCategoryName(String categoryName, Pageable pageable) {
        return bookService.findByCategoryName(categoryName, pageable);
    }
}
