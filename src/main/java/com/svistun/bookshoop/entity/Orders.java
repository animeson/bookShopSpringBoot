package com.svistun.bookshoop.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderID;
    private LocalDateTime orderDataTime;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name="person_ID")
    private User user;

    @OneToMany(mappedBy = "bookID")
    private Collection<Book> books;
}
