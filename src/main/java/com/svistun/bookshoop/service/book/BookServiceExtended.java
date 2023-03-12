package com.svistun.bookshoop.service.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Service
public interface BookServiceExtended extends BookService{
    void uploadBookPhotos(Collection<MultipartFile> files, Long bookID);
    @Transactional
    void addBookRating (Long bookID ,Double score);

}
