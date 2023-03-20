package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.ShoppingCart;
import com.svistun.bookshoop.entity.ShoppingCartStatus;
import com.svistun.bookshoop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByUserAndBookAndStatus(User user, Book book, ShoppingCartStatus active);
}
