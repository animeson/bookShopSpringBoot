package com.svistun.bookshoop.service.order;

import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.ShoppingCart;
import com.svistun.bookshoop.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface OrderService {
    @Transactional
    void saveOrder(ShoppingCart shoppingCart, Book book, User user);
/*    @Transactional
    void editOrder(ShoppingCart shoppingCart, Book book, User user);*/
}
