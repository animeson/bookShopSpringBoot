package com.svistun.bookshoop.controller.account;

import com.svistun.bookshoop.dto.UserAbbreviatedDto;
import com.svistun.bookshoop.dto.UserAccountDto;
import com.svistun.bookshoop.service.user.InfoUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final InfoUserService userService;
    @GetMapping
    public ResponseEntity<UserAccountDto> getUser(Authentication authentication) {
        return ResponseEntity.ok().body(userService.myAccount(authentication));
    }
    @GetMapping("/user-abbreviated-info")
    public ResponseEntity<UserAbbreviatedDto> getUserAbbreviatedInfo(Authentication authentication) {
        return ResponseEntity.ok().body(userService.getUserAbbreviatedInfo(authentication));
    }

}
