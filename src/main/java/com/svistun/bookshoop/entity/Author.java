package com.svistun.bookshoop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.time.LocalDate;

@Data
@Entity
@RequiredArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorID;
    private String lastName;
    private String firstName;
    private LocalDate dob;
    private String location;
    private String bio;

}
