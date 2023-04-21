package com.svistun.bookshoop.service.order;

import com.svistun.bookshoop.entity.Orders;

public interface OrderPriceUpdateService {
    Orders updateTotalCost(Orders orders);
}
