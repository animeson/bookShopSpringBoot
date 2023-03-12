package com.svistun.bookshoop.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketID;

    @ManyToOne
    @JoinColumn(name="user_ID")
    private User user;

    @OneToMany(mappedBy = "bookID")
    private Collection<Book> books;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private Status status;

}
