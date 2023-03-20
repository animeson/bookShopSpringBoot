package com.svistun.bookshoop.entity;
public enum OrderStatus {

    // Processed product
    IN_PROGRESS,
    // The customer confirmed the order
    APPROVED,
    // The customer paid for the order
    PAYED,
    // order sent
    SEND,
    // The customer canceled the order
    CANCELLED,
    // The order was delivered to the customer
    DELIVERED,
    // the product was returned for some reason
    RETURNED,
    // the customer was refunded a payment for the order
    REFUNDED,
    // the order was partially delivered to the customer
    PARTIALLY_DELIVERED


}
