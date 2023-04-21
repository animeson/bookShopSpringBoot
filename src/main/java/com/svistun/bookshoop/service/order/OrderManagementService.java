package com.svistun.bookshoop.service.order;

import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.ShoppingCart;
import com.svistun.bookshoop.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;


public interface OrderManagementService extends
        OrderPaymentService,
        OrderQueryService,
        OrderPriceUpdateService{
    @Transactional
    void saveOrder(Authentication authentication, Long bookID);
    @Transactional
    void editOrders(Book book, User user);
    void deleteShoppingCardInOrder(ShoppingCart shoppingCart);
    void deleteOrder(Long ordersID);
}
