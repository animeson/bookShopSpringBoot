package com.svistun.bookshoop.service.image;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.svistun.bookshoop.dropbox.TokenAccessService;
import com.svistun.bookshoop.dto.ImageDTO;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.BookImage;
import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.entity.UserImage;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService {
    @Value("${APP_NAME}")
    private String APP_NAME;

    private final TokenAccessService tokenAccessService;

    @Override
    public UserImage uploadUserPhoto(MultipartFile file, User user) {
        Collection<MultipartFile> files = new ArrayList<>();
        files.add(file);
        ImageDTO imageDTO = uploadPhoto(files, "users/id" + user.getUserID());
        return new UserImage()
                .setPath(imageDTO.path())
                .setFilename(imageDTO.filename())
                .setDataTimeUploads(LocalDateTime.now())
                .setMimeType(file.getContentType())
                .setUser(user)
                .setSize(file.getSize());
    }

    @Override
    public Collection<BookImage> uploadBookPhotos(@NotNull Collection<MultipartFile> files, Book book) {
        Collection<BookImage> bookImages = new ArrayList<>();
        for (MultipartFile file : files) {
            BookImage bookImage = new BookImage();
            ImageDTO imageDTO = uploadPhoto(Collections.singleton(file), "books/id" + book.getBookID());
            bookImage.setPath(imageDTO.path())
                    .setFilename(imageDTO.filename())
                    .setDataTimeUploads(LocalDateTime.now())
                    .setMimeType(file.getContentType())
                    .setSize(file.getSize())
                    .setIsPrimary(false)
                    .setBook(Collections.singleton(book));
            bookImages.add(bookImage);
        }
        return bookImages;
    }

    @Override
    public ImageDTO uploadPhoto(Collection<MultipartFile> files, String directory) {
        for (MultipartFile file : files) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            try {
                String uuid = UUID.randomUUID().toString();
                String modifiedFilename = uuid + "_" + filename;
                InputStream inputStream = file.getInputStream();
                DbxRequestConfig config = new DbxRequestConfig(APP_NAME);
                DbxClientV2 client = new DbxClientV2(config, tokenAccessService.getAccessToken());
                FileMetadata metadata = client.files().uploadBuilder("/home/images/" + directory + "/" + modifiedFilename)
                        .uploadAndFinish(inputStream);
                Metadata meta = client.files().getMetadata(metadata.getPathLower());
                SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings(meta.getPathLower());
                String imageUrl = sharedLinkMetadata.getUrl().replace("?dl=0", "?raw=1");
                return new ImageDTO(
                        modifiedFilename,
                        imageUrl
                );
            } catch (IOException | DbxException e) {
                throw new RuntimeException("Could not save file: " + filename, e);
            }
        }
        return null;
    }

    @Override
    public void deleteFile(String pathToFile) {
        // not method
    }

}

