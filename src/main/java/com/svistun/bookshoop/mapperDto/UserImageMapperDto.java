package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.ImageDTO;
import com.svistun.bookshoop.entity.UserImage;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserImageMapperDto implements Function<UserImage,ImageDTO> {

    @Override
    public ImageDTO apply(UserImage userImage) {
        return new ImageDTO(
                userImage.getFilename(),
                userImage.getUrl(),
                userImage.getPath()
        );
    }
}
