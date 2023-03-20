package com.svistun.bookshoop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long categoryID;
    private String name;
    private String title;
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "categories"
    )
    private Collection<Book> books;
}