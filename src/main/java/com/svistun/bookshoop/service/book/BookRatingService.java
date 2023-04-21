package com.svistun.bookshoop.service.book;

import org.springframework.transaction.annotation.Transactional;

public interface BookRatingService {
    @Transactional
    void addBookRating (Long bookID ,Double score);
}
