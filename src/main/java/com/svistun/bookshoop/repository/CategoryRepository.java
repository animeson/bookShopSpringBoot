package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    @NotNull
    List<Category> findAll();
    List<Category> findByName(String categoryName);
}
