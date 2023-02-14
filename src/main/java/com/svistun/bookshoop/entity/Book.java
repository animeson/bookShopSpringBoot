package com.svistun.bookshoop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;
    private String name;
    private String title;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(name = "author_book", joinColumns = {
            @JoinColumn(name = "book_id")
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id")})
    private List<Author> author;
    private String publisher;
    private Short years;
    private Short page;
    private String ISBN;
    private String binding;
    private String format;
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            }
    )
    @JoinTable(name = "book_categories",
            joinColumns = {
                    @JoinColumn(name = "book_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id")})
    @JsonIgnore
    private List<Category> categories;

    private Short weight;
    private String infoPublisher;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(name = "comment_book",
            joinColumns = {
                    @JoinColumn(name = "book_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "comments_id")
            }
    )
    @JsonIgnore
    private List<Comment> comments;
}

