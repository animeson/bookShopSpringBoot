package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.UserAccountDto;
import com.svistun.bookshoop.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
@Service
@RequiredArgsConstructor
public class UserAccountMapperDto implements Function<User, UserAccountDto> {
    private final UserImageMapperDto userImageMapperDto;
    @Override
    public UserAccountDto apply(User user) {
        return new UserAccountDto(
                user.getUserID(),
                user.getEmail(),
                user.getLastName(),
                user.getFirstName(),
                user.getGender(),
                user.getPhone(),
                user.getZipCode(),
                user.getLocation(),
                Optional.ofNullable(user.getUserImage())
                        .map(userImageMapperDto)
                        .orElse(null)

        );
    }
}
