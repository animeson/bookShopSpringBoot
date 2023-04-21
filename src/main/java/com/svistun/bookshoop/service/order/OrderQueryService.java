package com.svistun.bookshoop.service.order;

import com.svistun.bookshoop.dto.OrdersDto;

import java.util.Collection;

public interface OrderQueryService {
    Collection<OrdersDto> myOrdersByStatus(String email, String status);
}
