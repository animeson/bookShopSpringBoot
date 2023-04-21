package com.svistun.bookshoop.service.cart;

import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.ShoppingCart;
import com.svistun.bookshoop.entity.ShoppingCartStatus;
import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private static final Integer DEFAULT_QUANTITY = 1;


    @Override
    @Transactional
    public ShoppingCart addToCart(Book book, User user) {
        return ShoppingCart.builder()
                .user(user)
                .book(book)
                .cost(book.getCost())
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .quantity(DEFAULT_QUANTITY)
                .status(ShoppingCartStatus.ACTIVE)
                .build();
    }

    @Override
    public ShoppingCart findByShoppingCardID(Long shoppingCardID) {
        return shoppingCartRepository.findById(shoppingCardID)
                .orElseThrow(null);
    }

    @Override
    public void deleteShoppingCardByShoppingCardID(ShoppingCart shoppingCart) {
        shoppingCartRepository.delete(shoppingCart);
    }

}


