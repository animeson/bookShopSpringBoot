package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("SELECT w.book FROM Stock w WHERE w.book.bookID = :bookId")
    Optional<Book> findByBookId(@Param("bookId") Long bookId);

    @Query("SELECT w.book FROM Stock w order by w.book.bookID asc")
    Page<Book> findAllByBook(Pageable pageable);

    @Query("SELECT w.book FROM Stock w where w.book in (SELECT b FROM Book b JOIN b.categories c WHERE c.categoryID = :categoryId) order by w.book.bookID asc")
    Page<Book> findBookByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    @Query("SELECT w.book FROM Stock w where w.book in(SELECT b FROM Book b JOIN b.author c WHERE c.authorID = :authorID)order by w.book.bookID asc")
    Page<Book> findBookByAuthorID(@Param("authorID") Long authorID, Pageable pageable);
}