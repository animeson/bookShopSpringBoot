package com.svistun.bookshoop.service.shoppingCart;

import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.ShoppingCart;
import com.svistun.bookshoop.entity.ShoppingCartStatus;
import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.repository.ShoppingCartRepository;
import com.svistun.bookshoop.service.book.BookServiceImpl;
import com.svistun.bookshoop.service.order.OrderServiceImpl;
import com.svistun.bookshoop.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final Integer DEFAULT_QUANTITY = 1;
    private final UserServiceImpl userService;
    private final BookServiceImpl bookService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderServiceImpl orderService;

    @Override
    @Transactional
    public void addToCart(Long bookID, Authentication authentication) {
        Book book = bookService.getBookId(bookID);
        User user = userService.findByEmail(authentication.getName());
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserAndBookAndStatus(user, book, ShoppingCartStatus.ACTIVE);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user)
                    .setBook(book)
                    .setCost(book.getCost())
                    .setQuantity(DEFAULT_QUANTITY)
                    .setCreateAt(LocalDateTime.now())
                    .setUpdateAt(LocalDateTime.now())
                    .setStatus(ShoppingCartStatus.ACTIVE);
        } else {
            shoppingCart.setQuantity(shoppingCart.getQuantity() + DEFAULT_QUANTITY);
            shoppingCart.setCost(shoppingCart.getCost() + book.getCost());
            shoppingCart.setUpdateAt(LocalDateTime.now());
        }
        shoppingCartRepository.save(shoppingCart);
        orderService.saveOrder(shoppingCart, book, user);
    }

/*    @Override
    public void editToCart(Integer quantity) {
    }*/


}

