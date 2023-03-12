package com.svistun.bookshoop.controller.book;

import com.svistun.bookshoop.service.book.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookServiceImpl bookService;
    @PatchMapping("/{bookId}/upload-photo")
    public void uploadPhoto(
            @RequestParam("file") Collection<MultipartFile> files,
            @PathVariable Long bookId) {
        bookService.uploadBookPhotos(files, bookId);
    }

    @PatchMapping("/{bookId}/add-rating")
    public void addRating(
            @PathVariable Long bookId,
            @RequestParam ("score") Double score){
        bookService.addBookRating(bookId,score);
    }

}
