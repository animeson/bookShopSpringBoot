package com.svistun.bookshoop.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record ShoppingCartRequestDto(
        @Min(value = 1, message = "Min quantity = 1")
        @Max(value = 99, message = "Max quantity = 99")
        Integer quantity,
        String paymentMethod
  ) {
}
