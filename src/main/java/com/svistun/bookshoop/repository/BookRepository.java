package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @NotNull
    Page<Book> findAll(@NotNull Pageable pageable);

    Optional<Book> findByBookID(Long bookId);
    @Query("SELECT b FROM Book b JOIN b.author c WHERE c.firstName = :firstName AND c.lastName = :lastName")
    Page<Book> findAllByAuthorName(@Param("firstName") String firstName,
                                   @Param("lastName") String lastName, Pageable pageable);


    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.name = :categoryName")
    Page<Book> findAllByCategoryName(@Param("categoryName") String categoryName,Pageable pageable);



}
