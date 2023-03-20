package com.svistun.bookshoop.service.order;

import com.svistun.bookshoop.entity.*;
import com.svistun.bookshoop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public void saveOrder(ShoppingCart shoppingCart, Book book, User user) {
        // Check orders
        Orders orders = orderRepository.findByUserAndStatus(user,
                OrderStatus.IN_PROGRESS).orElse(null);
        if (orders == null) {
            orders = new Orders();
            orders.setUser(user)
            .setStatus(OrderStatus.IN_PROGRESS)
            .setPaymentMethod(PaymentMethod.NOT_SELECTED)
            .setTotalCost(shoppingCart.getCost())
            .setCreatedAt(LocalDateTime.now())
            .setUpdatedAt(LocalDateTime.now())
            .setShoppingCarts(Collections.singleton(shoppingCart));
        }
        else {
            // Check if there is a shoppingCart in orders
            boolean isShoppingCartExists = orders.getShoppingCarts().stream()
                    .anyMatch(cart -> cart.getBook().getBookID().equals(book.getBookID()));

            if (isShoppingCartExists) {
                // update shoppingCart
                orders.setTotalCost(orders.getTotalCost() + book.getCost())
                        .setUpdatedAt(LocalDateTime.now());
            } else {
                // add new shoppingCart
                orders.getShoppingCarts().add(shoppingCart);
                orders.setTotalCost(orders.getTotalCost() + shoppingCart.getCost())
                        .setUpdatedAt(LocalDateTime.now());
            }
        }
        orderRepository.save(orders);
    }
}
