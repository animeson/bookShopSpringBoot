package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    @NotNull
    List<Book> findAll();
    Optional<Book> findByBookID(Long bookId);



}
