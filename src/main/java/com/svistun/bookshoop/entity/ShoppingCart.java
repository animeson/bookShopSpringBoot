package com.svistun.bookshoop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketID;

    @ManyToOne
    @JoinColumn(name="user_ID")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Book book;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    private Integer quantity;

    private Double cost;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    @JsonIgnore
    private Orders orders;



}
