package com.svistun.bookshoop.service.user;

import com.svistun.bookshoop.entity.UserImage;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.UnknownHostException;

public interface UserServiceSetting  {
    @Transactional
    void editPassword(Authentication authentication, String password);
    @Transactional
    void uploadUserPhoto(MultipartFile file, Authentication authentication) throws UnknownHostException;
    @Transactional
    void deleteUserPhoto(UserImage userImage);
}
