package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.BookRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRatingRepository extends JpaRepository<BookRating, Long> {
}
