package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.ShoppingCart;
import com.svistun.bookshoop.entity.ShoppingCartStatus;
import com.svistun.bookshoop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndBookAndStatus(User user, Book book, ShoppingCartStatus status);
    Boolean existsByUserAndBookAndStatus(User user, Book book, ShoppingCartStatus status);
}
