package com.svistun.bookshoop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
public class BookRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookRatingID;
    private Double rating;
    private Integer count;
    @JsonIgnore
    @OneToMany(mappedBy = "rating")
    private Collection<Book> books;
}
