package com.svistun.bookshoop.service.user;

import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Override
    public Optional<User> findByEmail(String email) {
        if (userRepo.existsByEmail(email)) {
            return userRepo.findByEmail(email);
        } else {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }
    }

}