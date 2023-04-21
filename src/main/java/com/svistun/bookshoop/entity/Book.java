package com.svistun.bookshoop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collection;

@Data
@Entity
@RequiredArgsConstructor
@Accessors(chain = true)
public class Book {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long bookID;
    private String name;
    private String aboutBook;
    private String publisher;
    private Short years;
    private Short page;
    private String ISBN;
    private String binding;
    private String format;
    private Short weight;
    private String infoPublisher;
    private Double cost;
    @OneToOne
    @JoinColumn(name = "stock_id")
    @JsonIgnore
    private Stock stock;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "rating_id")
    private BookRating rating;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Collection<Author> author;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Collection<Category> categories;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "comment_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private Collection<Comment> comments;
    @ManyToMany(mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Collection<BookImage> images;


}

