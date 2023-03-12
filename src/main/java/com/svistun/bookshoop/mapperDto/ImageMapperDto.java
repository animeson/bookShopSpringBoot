package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.ImageBookDTO;
import com.svistun.bookshoop.entity.BookImage;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ImageMapperDto implements Function<BookImage, ImageBookDTO> {
    @Override
    public ImageBookDTO apply(BookImage image) {
        return new ImageBookDTO(
                image.getFilename(),
                image.getPath(),
                image.getIsPrimary(),
                image.getMimeType()
        );
    }
}
