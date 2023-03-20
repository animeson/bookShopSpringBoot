package com.svistun.bookshoop.service.shoppingCart;

import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

public interface ShoppingCartService {
    @Transactional
    void addToCart(Long bookID, Authentication authentication);



}
