package com.svistun.bookshoop.controller.account;

import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
@Log4j2
public class AccountController {
    private final UserServiceImpl userService;
    @GetMapping
    public ResponseEntity<Optional<User>> getUser(Authentication authentication) {
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/setting")
    public ResponseEntity<Optional<User>> settingUser(Authentication authentication) {
        return ResponseEntity.ok().body(null);
    }

    @PatchMapping("/upload-photo")
    public void uploadPhoto(@RequestParam("file") MultipartFile file,Authentication authentication) {
        userService.uploadUserPhoto(file,authentication);
    }
    @DeleteMapping("/photo")
    public void deletePhoto(Authentication authentication){
        userService.deleteUserPhoto(userService.findByEmail(authentication.getName()));
    }


}
