package com.svistun.bookshoop.controller.user;

import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
@Log4j2
public class UserController {
    private final UserServiceImpl userService;
    @GetMapping
    public ResponseEntity<Optional<User>> getUser(Authentication authentication) {
        return ResponseEntity.ok().body(userService.findByEmail(authentication.getName()));
    }

}
