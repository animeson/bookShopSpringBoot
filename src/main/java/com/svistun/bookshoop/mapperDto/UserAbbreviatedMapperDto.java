package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.UserAbbreviatedDto;
import com.svistun.bookshoop.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
@RequiredArgsConstructor
public class UserAbbreviatedMapperDto implements Function<User, UserAbbreviatedDto> {
    private final UserImageMapperDto userImageMapperDto;

    @Override
    public UserAbbreviatedDto apply(User user) {
        return  new UserAbbreviatedDto(
                user.getUserID(),
                user.getLastName(),
                user.getFirstName(),
                userImageMapperDto.apply(user.getUserImage()),
                user.getIsVerified()
        );
    }
}
