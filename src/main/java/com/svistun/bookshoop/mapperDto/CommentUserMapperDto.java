package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.UserCommentDto;
import com.svistun.bookshoop.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class CommentUserMapperDto implements Function<User, UserCommentDto> {

    @Override
    public UserCommentDto apply(User user) {
        return  new UserCommentDto(
                user.getUserID(),
                user.getLastName(),
                user.getFirstName()
        );
    }
}
