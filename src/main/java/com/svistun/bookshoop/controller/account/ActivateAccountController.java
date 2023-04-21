package com.svistun.bookshoop.controller.account;

import com.svistun.bookshoop.service.user.UserActivateAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account/activate")
public class ActivateAccountController {
    private final UserActivateAccount userService;
    @PostMapping
    public ResponseEntity<Void> activateAccount(
            Authentication authentication,
            @RequestParam("code") String code){
        userService.activateAccount(authentication, code);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/refresh-code")
    public ResponseEntity<Void> refreshCode(
            Authentication authentication){
        userService.refreshCodeActivateAccount(authentication);
        return ResponseEntity.ok().build();
    }
}
