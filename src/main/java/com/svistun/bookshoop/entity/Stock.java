package com.svistun.bookshoop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockID;
    @OneToOne
    @JoinColumn(name = "book_ID")
    @JsonBackReference
    private Book book;
    private Integer quantity;

}
