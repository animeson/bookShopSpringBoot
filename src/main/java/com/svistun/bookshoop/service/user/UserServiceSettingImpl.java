package com.svistun.bookshoop.service.user;

import com.svistun.bookshoop.config.security.ApplicationConfig;
import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.entity.UserImage;
import com.svistun.bookshoop.exception.InvalidDataException;
import com.svistun.bookshoop.repository.UserImageRepository;
import com.svistun.bookshoop.repository.UserRepository;
import com.svistun.bookshoop.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class UserServiceSettingImpl implements UserServiceSetting {
    private final ApplicationConfig applicationConfig;
    private final UserRepository userRepo;
    private final ImageService imageService;
    private final UserImageRepository userImageRepository;

    @Override
    @Transactional
    public void uploadUserPhoto(MultipartFile file, Authentication authentication) {
        User user = userRepo.findByEmail(authentication.getName())
                .orElseThrow();
        if (user.getUserImage() != null) {
            deleteUserPhoto(user.getUserImage());
        }
        if (!file.isEmpty()) {
            UserImage image = imageService.uploadUserPhoto(file, user);
            user.setUserImage(image);
            userRepo.save(user);
        } else {
            throw new InvalidDataException("Select a file");
        }
    }

    @Override
    @Transactional
    public void deleteUserPhoto(UserImage userImage) {
        try {
            userRepo.removeUserImage(userImage);
            userImageRepository.deleteByUser(userImage.getUser().getUserID());
            imageService.deleteFile(userImage.getPath());
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file");
        }
    }


    @Override
    @Transactional
    public void editPassword(Authentication authentication, String password) {
        User user = userRepo.findByEmail(authentication.getName())
                .orElseThrow();
        user.setPassword(applicationConfig.passwordEncoder().encode(password));
    }




}