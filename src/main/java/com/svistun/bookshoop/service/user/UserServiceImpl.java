package com.svistun.bookshoop.service.user;

import com.svistun.bookshoop.dto.ImageDTO;
import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.entity.UserImage;
import com.svistun.bookshoop.repository.UserRepository;
import com.svistun.bookshoop.service.image.ImageServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final ImageServiceImp imageServiceImp;

    //TODO: divide user setting on security, person information, edit email.
    @Override
    public User findByEmail(String email) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        return userOptional.orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public void uploadUserPhoto(MultipartFile file, Authentication authentication) {
        User user = findByEmail(authentication.getName());
        if (user.getUserImage() != null) {
            deleteUserPhoto(user);
        }
        // Update the user's photo
        UserImage imageDTO = imageServiceImp.uploadUserPhoto(file, user);
        user.setUserImage(imageDTO);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void deleteUserPhoto(User user) {
        imageServiceImp.deleteFile(user.getUserImage().getPath());
        userRepo.removeUserImage(user.getUserImage());
/*        userRepo.deleteImageById(user.getUserImage().getUserImageID());*/
    }


}