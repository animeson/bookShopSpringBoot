package com.svistun.bookshoop.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketID;

    @ManyToOne
    @JoinColumn(name="person_ID")
    private Person person;

    @OneToMany(mappedBy = "bookID")
    private Set<Book> books;

    private PaymentMethod paymentMethod;

    private Status status;

}
