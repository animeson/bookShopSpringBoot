package com.svistun.bookshoop.controller.account;

import com.svistun.bookshoop.controller.auth.RegisterRequest;
import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.service.user.UserServiceSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.UnknownHostException;
import java.util.Optional;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account/setting")
public class SettingAccountController {
    private final UserServiceSetting userService;
    @PostMapping("/edit-password")
    public ResponseEntity<Optional<User>> settingUser(Authentication authentication,
                                                      @RequestBody RegisterRequest registerRequest) {
        userService.editPassword(authentication, registerRequest.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/upload-photo")
    public void uploadPhoto(@RequestParam("file") MultipartFile file,
                            Authentication authentication) throws UnknownHostException {
        userService.uploadUserPhoto(file, authentication);
    }
}
