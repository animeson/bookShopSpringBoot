package com.svistun.bookshoop.dto.user;


import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateUserDto {
    @Email(message = "E-mail should not be empty")
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank(message = "New password is mandatory")
    @Column(nullable = false)
    private String password;


}
