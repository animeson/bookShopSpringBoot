package com.svistun.bookshoop.service.image;

import com.svistun.bookshoop.dto.ImageDTO;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.BookImage;
import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.entity.UserImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@Service
public interface ImageService {
    UserImage uploadUserPhoto(MultipartFile file, User user);
    Collection<BookImage> uploadBookPhotos(Collection<MultipartFile> files, Book book);
    ImageDTO uploadPhoto(Collection<MultipartFile> files, String directory);
    void deleteFile(String pathToFile);

}
