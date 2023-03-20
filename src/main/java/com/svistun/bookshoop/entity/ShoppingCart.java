package com.svistun.bookshoop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
@Accessors(chain = true)
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketID;

    @ManyToOne
    @JoinColumn(name="user_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Book book;
    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;
    private Integer quantity;
    private Double cost;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


}
