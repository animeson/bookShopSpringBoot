package com.svistun.bookshoop.dto;

public record ShoppingCartDto(
        Long shoppingCartID,
        BookMainPageDto books,
        Integer quantity,
        Double cost
) {
}
