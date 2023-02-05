package com.svistun.bookshoop.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svistun.bookshoop.dto.user.CreateUserDto;
import com.svistun.bookshoop.entity.Person;
import com.svistun.bookshoop.entity.Role;
import com.svistun.bookshoop.facade.PersonFacade;
import com.svistun.bookshoop.filter.PersonAuthenticationFilter;
import com.svistun.bookshoop.filter.PersonAuthorizationFilter;
import com.svistun.bookshoop.service.user.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Log4j2
public class AuthController {
    private final PersonServiceImpl userService;

    @PostMapping("/register")
    public void register(@RequestBody CreateUserDto createUserDto, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Person person = userService.createUser(createUserDto);
        List<String> roles = person.getRoles().stream().map(Role::getAuthority).toList();
        PersonFacade.token(request, response, person, roles);
        log.info("a new user {} with the role was created", person.getEmail());

    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring(7);
                DecodedJWT decodedJWT = PersonAuthorizationFilter.verifyToken(refresh_token);
                Person person = userService.findByEmail(decodedJWT.getSubject());
                List<String> roles = person.getRoles().stream().map(Role::getAuthority).toList();
                PersonFacade.token(request, response,person, roles);
            } catch (Exception exception) {
                throw new RuntimeException("Can't write response");
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

}