package com.svistun.bookshoop.service.user;

import com.svistun.bookshoop.entity.User;

public interface UserService {
    User findByEmail(String email);
}
