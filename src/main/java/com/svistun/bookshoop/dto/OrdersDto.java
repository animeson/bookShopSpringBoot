package com.svistun.bookshoop.dto;

import com.svistun.bookshoop.entity.OrderStatus;
import com.svistun.bookshoop.entity.PaymentMethod;

import java.util.Collection;

public record OrdersDto(
        Long orderID,
        OrderStatus status,
        Double totalCost,
        Collection<ShoppingCartDto> shoppingCarts,
        String createdAt,
        String updatedAt,
        PaymentMethod paymentMethod
) {
}
