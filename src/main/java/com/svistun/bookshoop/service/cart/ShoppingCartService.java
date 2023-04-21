package com.svistun.bookshoop.service.cart;

import com.svistun.bookshoop.entity.*;
import org.springframework.transaction.annotation.Transactional;

public interface ShoppingCartService {
    @Transactional
    ShoppingCart addToCart(Book book, User user);
    ShoppingCart findByShoppingCardID(Long shoppingCardID);
    @Transactional
    void deleteShoppingCardByShoppingCardID(ShoppingCart shoppingCart);



}
