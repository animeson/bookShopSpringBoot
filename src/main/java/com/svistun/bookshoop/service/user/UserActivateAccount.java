package com.svistun.bookshoop.service.user;

import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

public interface UserActivateAccount {
    @Transactional
    void activateAccount(Authentication authentication, String code);

    @Transactional
    void refreshCodeActivateAccount(Authentication authentication);
}
