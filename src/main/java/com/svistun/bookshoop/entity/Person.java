package com.svistun.bookshoop.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personID;
    @Email(message = "E-mail should not be empty")
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank(message = "New password is mandatory")
    @Column(nullable = false,length = 100)
    private String password;
    private LocalDateTime registerDate;
    @Column(length = 50)
    private String lastName;
    @Column(length = 50)
    private String firstName;
    private Boolean gender;
    @Column(length = 100)
    private String location;
    @Column(length = 25)
    private String phone;
    @Column(length = 25)
    private Integer zipCode;
    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "person_role", joinColumns = @JoinColumn(name = "person_ID"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
