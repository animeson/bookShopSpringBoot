package com.svistun.bookshoop.service.user;


import com.svistun.bookshoop.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findByEmail(String email);

}
