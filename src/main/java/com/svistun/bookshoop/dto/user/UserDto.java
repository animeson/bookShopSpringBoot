package com.svistun.bookshoop.dto.user;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDto {
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
}
