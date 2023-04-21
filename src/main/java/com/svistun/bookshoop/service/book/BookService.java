package com.svistun.bookshoop.service.book;

import com.svistun.bookshoop.entity.Book;

public interface BookService extends
        BookPhotoService,
        BookRatingService,
        BookMainPageService {
    Book getBookId(Long bookId);


}
