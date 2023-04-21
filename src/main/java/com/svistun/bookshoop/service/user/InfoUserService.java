package com.svistun.bookshoop.service.user;

import com.svistun.bookshoop.dto.UserAbbreviatedDto;
import com.svistun.bookshoop.dto.UserAccountDto;
import org.springframework.security.core.Authentication;

public interface InfoUserService extends UserService{
    UserAccountDto myAccount(Authentication authentication);
    UserAbbreviatedDto getUserAbbreviatedInfo(Authentication authentication);
}
