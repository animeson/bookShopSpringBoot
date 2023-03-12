package com.svistun.bookshoop.service.user;


import com.svistun.bookshoop.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public interface UserService {
    User findByEmail(String email);
    @Transactional
    void uploadUserPhoto(MultipartFile file, Authentication authentication);
    @Transactional
    void deleteUserPhoto(User user);



}
