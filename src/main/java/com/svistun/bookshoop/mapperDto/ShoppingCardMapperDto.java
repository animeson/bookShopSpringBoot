package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.ShoppingCartDto;
import com.svistun.bookshoop.entity.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;
@Service
@RequiredArgsConstructor
public class ShoppingCardMapperDto implements Function<ShoppingCart, ShoppingCartDto> {
    private final BookMainMapperDto bookMainMapperDto;
    private final DateTimeFormatter dateFormat = DateTimeFormatter
            .ofPattern("dd.MM.yyyy : hh.mm a");

    @Override
    public ShoppingCartDto apply(ShoppingCart shoppingCart) {
        return new ShoppingCartDto(
                shoppingCart.getBasketID(),
                bookMainMapperDto.apply(shoppingCart.getBook()),
                shoppingCart.getQuantity(),
                shoppingCart.getCost(),
                shoppingCart.getCreateAt().format(dateFormat),
                shoppingCart.getUpdateAt().format(dateFormat)
        );
    }
}
