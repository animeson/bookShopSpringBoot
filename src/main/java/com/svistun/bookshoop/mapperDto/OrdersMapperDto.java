package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.OrdersDto;
import com.svistun.bookshoop.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersMapperDto implements Function<Orders, OrdersDto> {
    private final ShoppingCardMapperDto shoppingCardMapperDto;
    private final DateTimeFormatter dateFormat = DateTimeFormatter
            .ofPattern("dd.MM.yyyy : hh.mm a");

    @Override
    public OrdersDto apply(Orders orders) {
        return new OrdersDto(
                orders.getOrderID(),
                orders.getStatus(),
                orders.getTotalCost(),
                orders.getShoppingCarts()
                        .stream()
                        .map(shoppingCardMapperDto)
                        .collect(Collectors.toList()),
                orders.getCreatedAt().format(dateFormat),
                orders.getUpdatedAt().format(dateFormat),
                orders.getPaymentMethod()

        );
    }
}
