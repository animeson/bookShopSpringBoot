package com.svistun.bookshoop.controller.catalog;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.service.category.CatalogServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/catalog")
@Log4j2
public class CatalogController {
    private final CatalogServiceImpl catalogService;

    @GetMapping()
    public ResponseEntity<Map<String,List<?>>> findAllBook(/*
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size)
            PageRequest.of(page, size, Sort.by("BookID"))*/) {
        return ResponseEntity.ok(catalogService.getMainPageCatalog());
    }

    @GetMapping("book/{bookId}")
    public ResponseEntity <BookDto> getBookByBookId(@PathVariable Long bookId) {
        return ResponseEntity.ok(catalogService.getBookByBookId(bookId));
    }
    @GetMapping("category/{categoryName}")
    public ResponseEntity<List<BookMainPageDto>> getBookByCategoryId(@PathVariable String categoryName) {
        return ResponseEntity.ok(catalogService.getBookByCategoryName(categoryName));
    }
}
