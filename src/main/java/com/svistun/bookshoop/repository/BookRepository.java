package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
