package com.svistun.bookshoop.controller.book;

import com.svistun.bookshoop.service.book.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookServiceImpl bookService;

    @PatchMapping("/{bookID}/upload-photo")
    public ResponseEntity<Void> uploadPhoto(
            @RequestParam("file") Collection<MultipartFile> files,
            @PathVariable Long bookID) {
        bookService.uploadBookPhotos(files, bookID);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{bookID}/add-rating")
    public ResponseEntity<Void> addRating(
            @PathVariable Long bookID,
            @RequestParam("score") Double score) {
        bookService.addBookRating(bookID, score);
        return ResponseEntity.ok().build();
    }

}
