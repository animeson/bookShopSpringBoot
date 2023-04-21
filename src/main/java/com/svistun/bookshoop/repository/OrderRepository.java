package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.OrderStatus;
import com.svistun.bookshoop.entity.Orders;
import com.svistun.bookshoop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByUserAndStatus(User user, OrderStatus status);
    Collection<Orders> findAllByUserAndStatus(User user, OrderStatus status);
    Boolean existsByUserAndStatus(User user, OrderStatus status);
    Optional<Orders> findByUser(User user);
    Optional<Orders> findByOrderID(Long id);



}
