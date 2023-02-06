package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    @NotNull
    List<Book> findAll();
}
