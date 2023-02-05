package com.svistun.bookshoop.service.user;


import com.svistun.bookshoop.dto.user.CreateUserDto;
import com.svistun.bookshoop.dto.user.UserDto;
import com.svistun.bookshoop.entity.Person;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface PersonService {
    @Transactional
    Person createUser(CreateUserDto user);

    Person findByEmail(String email);

    @Transactional
    void editUser(Authentication authentication,UserDto userDto);

    UserDto getUser(Authentication authentication);

    @Transactional
    void updatePassword(String password, String oldPassword, String email);

    Boolean isUser(String email);

}
