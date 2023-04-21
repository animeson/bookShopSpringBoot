package com.svistun.bookshoop.service.user;

import com.svistun.bookshoop.dto.UserAbbreviatedDto;
import com.svistun.bookshoop.dto.UserAccountDto;
import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.mapperDto.UserAbbreviatedMapperDto;
import com.svistun.bookshoop.mapperDto.UserAccountMapperDto;
import com.svistun.bookshoop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InfoUserServiceImpl implements InfoUserService{
    private final UserAccountMapperDto userDto;
    private final UserAbbreviatedMapperDto abbreviatedMapperDto;
    private final UserRepository userRepository;
    @Override
    public UserAccountDto myAccount(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName())
                .map(userDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserAbbreviatedDto getUserAbbreviatedInfo(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName())
                .map(abbreviatedMapperDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NullPointerException("User is null"));
    }
}
