package com.svistun.bookshoop.controller;

import com.svistun.bookshoop.dto.user.UserUpdatePassword;
import com.svistun.bookshoop.dto.user.UserDto;
import com.svistun.bookshoop.entity.Person;
import com.svistun.bookshoop.entity.Role;
import com.svistun.bookshoop.facade.PersonFacade;
import com.svistun.bookshoop.service.user.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
@Log4j2
public class UserController {
    private final PersonServiceImpl userService;
    private final PersonFacade personFacade;

    @GetMapping
    public ResponseEntity<UserDto> getUser(@NotNull Authentication authentication) {
        return ResponseEntity.ok().body(userService.getUser(authentication));
    }

    @PatchMapping
    public ResponseEntity<Person> editUser(@NotNull Authentication authentication, @RequestBody UserDto userDto) {
        userService.editUser(authentication, userDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update-password")
    public void updatePassword(HttpServletRequest request, HttpServletResponse response,
                                                 @RequestBody UserUpdatePassword updatePassword,
                               Authentication authentication) {
        try {
            personFacade.updatePassword(updatePassword, authentication);
            Person person;
            person = userService.findByEmail(authentication.getName());
            PersonFacade.token(request, response, person,
                    person.getRoles().stream().map(Role::getAuthority).toList());

        } catch (Exception exception) {
            log.warn("the old password is wrong, try again");
            throw new IllegalArgumentException("the old password is wrong, try again");

        }
    }


}
