package com.svistun.bookshoop.service.user;

import com.svistun.bookshoop.dto.user.CreateUserDto;
import com.svistun.bookshoop.dto.user.UserDto;
import com.svistun.bookshoop.entity.Person;
import com.svistun.bookshoop.entity.Role;
import com.svistun.bookshoop.facade.PersonFacade;
import com.svistun.bookshoop.repository.PersonRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.svistun.bookshoop.facade.PersonFacade.copyProperties;

@Service
@Log4j2
public class PersonServiceImpl implements PersonService, UserDetailsService {
    private final PersonRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public PersonServiceImpl(PersonRepo userRepo,
                             @Lazy PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = userRepo.findByEmail(email);
        if (person == null) {
            throw new UsernameNotFoundException("User not found exception");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        person.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getAuthority())));
        return new User(person.getEmail(), person.getPassword(), authorities);
    }

    @Override
    @Transactional
    public Person createUser(CreateUserDto createUserDto) {
        Person person = new Person();
        person.setEmail(createUserDto.getEmail());
        person.setLastName("#scribe#"); //default last name
        person.setFirstName(String.valueOf(createUserDto.getEmail().hashCode()).substring(1)); //default first name
        person.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        person.setRegisterDate(LocalDateTime.now());
        person.setRoles(Collections.singleton(Role.USER));
        userRepo.save(person);
        return person;
    }

    @Override
    public Person findByEmail(String email) {
        if (userRepo.existsByEmail(email)) {
            return userRepo.findByEmail(email);
        } else {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    @Transactional
    public void editUser(Authentication authentication, UserDto userDto) {
        Person user = findByEmail(authentication.getName());
        if (user != null) {
            copyProperties(userDto, user);
            userRepo.save(user);
        }

    }

    @Override
    public UserDto getUser(Authentication authentication) {
        Person user = findByEmail(authentication.getName());
        UserDto userDto = new UserDto();
        copyProperties(user, userDto);
        return userDto;

    }

    //TODO: edit update password, old pass != new pass
    @Override
    @Transactional
    public void updatePassword(String password, String oldPassword, String email) {
        Person user = findByEmail(email);
        System.out.println(passwordEncoder.matches(password, user.getPassword()));

 /*       if (passwordEncoder.matches(password, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(password));
            userRepo.save(user);
        } else {
            log.warn("the old password is wrong, try again");
            *//* throw new IllegalArgumentException("the old password is wrong, try again");*//*
        }*/

    }

    @Override
    public Boolean isUser(String email) {
        return userRepo.existsByEmail(email);
    }

}