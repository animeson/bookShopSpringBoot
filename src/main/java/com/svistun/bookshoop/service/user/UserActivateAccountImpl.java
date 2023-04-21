package com.svistun.bookshoop.service.user;

import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.entity.UserVerifiedCode;
import com.svistun.bookshoop.exception.InvalidDataException;
import com.svistun.bookshoop.repository.CodeRepository;
import com.svistun.bookshoop.repository.UserRepository;
import com.svistun.bookshoop.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserActivateAccountImpl implements UserActivateAccount{
    private final CodeRepository codeRepository;
    private final UserRepository userRepo;
    private final EmailService emailService;

    @Override
    @Transactional
    public void activateAccount(Authentication authentication, String code) {
        User user = userRepo.findByEmail(authentication.getName()).orElseThrow();
        if (user.getIsVerified() || code == null){
            throw new InvalidDataException("Account activate");
        }
        UserVerifiedCode userVerifiedCode = codeRepository.getByCode(code)
                .orElseThrow(()-> new InvalidDataException("Invalid code"));
        if (!code.equals(userVerifiedCode.getCode()) &&
                userVerifiedCode.getUser() != user) {
            throw new InvalidDataException("Invalid code");
        }
        user.setIsVerified(true);
        userRepo.save(user);
        codeRepository.delete(userVerifiedCode);
        emailService.sendMessage(user, "Account activate", " You account activate." +
                "Enjoy your time on our platform and find a book to your liking");
    }

    @Override
    @Transactional
    public void refreshCodeActivateAccount(Authentication authentication) {
        User user = userRepo.findByEmail(authentication.getName()).orElseThrow();
        if (user.getIsVerified()){
            throw new InvalidDataException("Account activate");
        }
        UserVerifiedCode userVerifiedCode = codeRepository.getByUser(user);
        if (userVerifiedCode != null){
            codeRepository.delete(userVerifiedCode);
        }
        emailService.sendRegisterCode(user);


    }
}
