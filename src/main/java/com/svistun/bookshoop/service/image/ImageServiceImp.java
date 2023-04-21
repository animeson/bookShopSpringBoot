package com.svistun.bookshoop.service.image;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.svistun.bookshoop.config.dropbox.DbxClientProvider;
import com.svistun.bookshoop.dto.ImageDTO;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.BookImage;
import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.entity.UserImage;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService {

    private final DbxClientProvider dbxClientProvider;
    @Override
    @Transactional
    public UserImage uploadUserPhoto(MultipartFile file, User user) {
        Collection<MultipartFile> files = new ArrayList<>();
        files.add(file);
        ImageDTO imageDTO = uploadPhoto(files, "users/id" + user.getUserID());
        return UserImage.builder()
                .url(imageDTO.url())
                .filename(imageDTO.filename())
                .dataTimeUploads(LocalDateTime.now())
                .mimeType(file.getContentType())
                .user(user)
                .size(file.getSize())
                .path(imageDTO.path())
                .build();
    }

    @Override
    @Transactional
    public Collection<BookImage> uploadBookPhotos(@NotNull Collection<MultipartFile> files, Book book) {
        Collection<BookImage> bookImages = new ArrayList<>();
        for (MultipartFile file : files) {
            ImageDTO imageDTO = uploadPhoto(Collections.singleton(file), "books/id" + book.getBookID());
            var bookImage = BookImage.builder()
                    .url(imageDTO.url())
                    .filename(imageDTO.filename())
                    .dataTimeUploads(LocalDateTime.now())
                    .mimeType(file.getContentType())
                    .size(file.getSize())
                    .isPrimary(false)
                    .book(Collections.singleton(book))
                    .path(imageDTO.path())
                    .build();
            bookImages.add(bookImage);
        }
        return bookImages;
    }

    @Override
    @Transactional
    public ImageDTO uploadPhoto(Collection<MultipartFile> files, String directory) {
        for (MultipartFile file : files) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            try {
                String uuid = UUID.randomUUID().toString();
                String modifiedFilename = uuid + "_" + filename;
                InputStream inputStream = file.getInputStream();
                DbxClientV2 client = dbxClientProvider.getClient();
                FileMetadata metadata = client.files().uploadBuilder("/home/images/" + directory + "/" + modifiedFilename)
                        .uploadAndFinish(inputStream);
                Metadata meta = client.files().getMetadata(metadata.getPathLower());
                SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings(meta.getPathLower());
                String imageUrl = sharedLinkMetadata.getUrl().replace("?dl=0", "?raw=1");
                return new ImageDTO(
                        modifiedFilename,
                        imageUrl,
                        sharedLinkMetadata.getPathLower()
                );
            } catch (IOException | DbxException e) {
                throw new RuntimeException("Could not save file: " + filename, e);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteFile(String pathToFile) {
        DbxClientV2 client = dbxClientProvider.getClient();
        try {
            client.files().deleteV2(pathToFile);
        } catch (DbxException e) {
            throw new RuntimeException("Failed to delete file");
        }
    }

}

