package com.svistun.bookshoop.controller.catalog;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.dto.CategoryDto;
import com.svistun.bookshoop.dto.PageableFactory;
import com.svistun.bookshoop.service.catalog.CatalogServiceImpl;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/catalog/books")
public class CatalogController {
    private final CatalogServiceImpl catalogService;

    @GetMapping
    public ResponseEntity<Page<BookMainPageDto>> findAllBook(
            @RequestParam(value = "page", defaultValue = "0") @Min(0) int page,
            @RequestParam(value = "size", defaultValue = "16") @Min(1) @Max(20) int size) {
        return ResponseEntity.ok().body(catalogService.getAllBookMainPage(PageableFactory.create(page, size)));
    }

    @GetMapping("/{bookID}")
    public ResponseEntity<BookDto> getBookByBookId(
            @PathVariable Long bookID) {
        return ResponseEntity.ok(catalogService.getBookByBookId(bookID));
    }

    @GetMapping("/category/{categoryID}")
    public ResponseEntity<Page<BookMainPageDto>> getBookByCategoryId(
            @PathVariable Long categoryID,
            @RequestParam(value = "page", defaultValue = "0") @Min(0) int page,
            @RequestParam(value = "size", defaultValue = "16") @Min(1) @Max(20) int size) {
        return ResponseEntity.ok(catalogService.findByCategoryID(categoryID,
                PageableFactory.create(page, size)));
    }

    @GetMapping("/category")
    public ResponseEntity<Collection<CategoryDto>> getCategory() {
        return ResponseEntity.ok().body(catalogService.getAllCategory());
    }

    @GetMapping("/author/{authorID}")
    public ResponseEntity<Page<BookMainPageDto>> getBookByAuthorID(
            @PathVariable Long authorID,
            @RequestParam(value = "page", defaultValue = "0") @Min(0) int page,
            @RequestParam(value = "size", defaultValue = "16") @Min(1) @Max(20) int size) {

        return ResponseEntity.ok(catalogService.findByAuthorID(authorID,PageableFactory.create(page, size)));
    }

}
