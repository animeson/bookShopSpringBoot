package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.BookRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingRepository extends JpaRepository<BookRating, Long> {
}
