package com.svistun.bookshoop.service.order;

import com.svistun.bookshoop.dto.OrdersDto;
import com.svistun.bookshoop.entity.*;
import com.svistun.bookshoop.exception.InvalidDataException;
import com.svistun.bookshoop.mapperDto.OrdersMapperDto;
import com.svistun.bookshoop.repository.OrderRepository;
import com.svistun.bookshoop.service.book.BookService;
import com.svistun.bookshoop.service.cart.ShoppingCartService;
import com.svistun.bookshoop.service.email.EmailService;
import com.svistun.bookshoop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderManagementService {
    private final OrderRepository orderRepository;
    private final OrdersMapperDto ordersMapperDto;
    private final UserService userService;
    private final BookService bookService;
    private final ShoppingCartService shoppingCartService;
    private final EmailService emailService;

    @Override
    @Transactional
    public void saveOrder(Authentication authentication, Long bookID) {
        User user = userService.findByEmail(authentication.getName());
        Book book = bookService.getBookId(bookID);
        if (!orderRepository.existsByUserAndStatus(user, OrderStatus.IN_PROGRESS)) {
            var orders = Orders.builder()
                    .createdAt(LocalDateTime.now())
                    .paymentMethod(PaymentMethod.NOT_SELECTED)
                    .updatedAt(LocalDateTime.now())
                    .status(OrderStatus.IN_PROGRESS)
                    .user(user)
                    .build();
            orders.setShoppingCarts(Collections.singleton(shoppingCartService.addToCart(book, user)
                    .setOrders(orders)));
            orders.setTotalCost(updateTotalCost(orders).getTotalCost());
            orderRepository.save(orders);
        } else {
            editOrders(book, user);
        }


    }

    @Override
    @Transactional
    //TODO:: fixed bug when the same product
    public void editOrders(Book book, User user) {
        Orders orders = orderRepository
                .findByUserAndStatus(user, OrderStatus.IN_PROGRESS)
                .orElseThrow(null);
        var shoppingCart = shoppingCartService
                .addToCart(book, user)
                .setOrders(orders);
        updateTotalCost(orders)
                .getShoppingCarts()
                .add(shoppingCart);
    }

    @Override
    @Transactional
    public Orders updateTotalCost(Orders orders) {
        return orders.setTotalCost(orders.getShoppingCarts()
                        .stream()
                        .mapToDouble(ShoppingCart::getCost)
                        .sum())
                .setUpdatedAt(LocalDateTime.now());
    }


    @Override
    public Collection<OrdersDto> myOrdersByStatus(String email, String status) {
        return orderRepository.findAllByUserAndStatus(userService.findByEmail(email),
                OrderStatus.valueOf(status))
                .stream()
                .map(ordersMapperDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteShoppingCardInOrder(ShoppingCart shoppingCart) {

    }

    @Override
    @Transactional
    public void payOrder(Long ordersID) {
        Orders orders = orderRepository.findById(ordersID)
                .orElseThrow(null);

        orders.setStatus(OrderStatus.PAYED)
                .getShoppingCarts()
                .forEach(shoppingCart -> shoppingCart
                        .setStatus(ShoppingCartStatus.COMPLETED));
        orderRepository.save(orders);
        emailService.sendMessage(orders.getUser(), "Pay",
                ordersMapperDto.apply(orders).toString());

    }

    @Override
    @Transactional
    public void deleteOrder(Long ordersID) {
        Orders orders = orderRepository.findByOrderID(ordersID)
                .orElseThrow(() -> new InvalidDataException("Orders is null"));
        orders.getShoppingCarts().forEach(shoppingCart -> shoppingCart.setBook(null));
        orderRepository.delete(orders);
    }
}
