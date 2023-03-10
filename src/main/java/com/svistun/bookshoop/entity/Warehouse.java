package com.svistun.bookshoop.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseID;
    @OneToOne
    @JoinColumn(name="book_ID")
    private Book book;
    private Integer quantity;
    private Double cost;

}
