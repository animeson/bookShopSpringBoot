package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.entity.UserVerifiedCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface CodeRepository extends JpaRepository<UserVerifiedCode, Long> {
    Optional<UserVerifiedCode> getByCode(String code);
    UserVerifiedCode getByUser(User user);
}
