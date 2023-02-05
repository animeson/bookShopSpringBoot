package com.svistun.bookshoop.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;
    private String name;
    @ManyToOne
    @JoinColumn(name="author_ID", nullable=false)
    private Author author;

    @OneToMany(mappedBy = "categoryID")
    private Set<Category> categories;
}

