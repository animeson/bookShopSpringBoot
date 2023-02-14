package com.svistun.bookshoop.controller.catalog;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.dto.PageableFactory;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.response.MainPageResponse;
import com.svistun.bookshoop.service.catalog.CatalogServiceImpl;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/catalog")
@Log4j2
public class CatalogController {
    private final CatalogServiceImpl catalogService;

    @GetMapping
    public ResponseEntity<MainPageResponse> findAllBook(
            @RequestParam(value = "page", defaultValue = "0") @Min(0) int page,
            @RequestParam(value = "size", defaultValue = "5") @Min(1) @Max(20) int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "bookID");
        return ResponseEntity.ok(catalogService.getMainPageCatalog(PageableFactory
                .create(page, size, sort)));
    }

    @GetMapping("book/{bookId}")
    public ResponseEntity<BookDto> getBookByBookId(
            @PathVariable Long bookId) {
        return ResponseEntity.ok(catalogService.getBookByBookId(bookId));
    }

    @GetMapping("category/{categoryName}")
    public ResponseEntity<Page<BookMainPageDto>> getBookByCategoryId(
            @PathVariable String categoryName,
            @RequestParam(value = "page", defaultValue = "0") @Min(0) int page,
            @RequestParam(value = "size", defaultValue = "5") @Min(1) @Max(20) int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "bookID");
        return ResponseEntity.ok(catalogService.getBookByCategoryName(categoryName,
                PageableFactory.create(page, size, sort)));
    }
}
