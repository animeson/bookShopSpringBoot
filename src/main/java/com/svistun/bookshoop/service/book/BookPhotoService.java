package com.svistun.bookshoop.service.book;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface BookPhotoService  {
    @Transactional
    void uploadBookPhotos(Collection<MultipartFile> files, Long bookID);


}
