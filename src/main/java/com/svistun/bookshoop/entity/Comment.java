package com.svistun.bookshoop.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;
    private String comment;
    @ManyToOne
    @JoinColumn(name="person_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name="book_ID")
    private Book book;
    private LocalDateTime dateTimeComment;

}
