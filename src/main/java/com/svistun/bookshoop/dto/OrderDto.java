package com.svistun.bookshoop.dto;

import com.svistun.bookshoop.entity.ShoppingCart;

import java.util.Collection;

public record OrderDto(
        Collection<ShoppingCart> shoppingCarts
) {
}
