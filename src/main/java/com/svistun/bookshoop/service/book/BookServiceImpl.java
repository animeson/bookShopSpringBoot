package com.svistun.bookshoop.service.book;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.BookImage;
import com.svistun.bookshoop.entity.BookRating;
import com.svistun.bookshoop.exception.InvalidDataException;
import com.svistun.bookshoop.mapperDto.BookMainMapperDto;
import com.svistun.bookshoop.mapperDto.BookMapperDto;
import com.svistun.bookshoop.repository.BookRatingRepository;
import com.svistun.bookshoop.repository.BookRepository;
import com.svistun.bookshoop.service.image.ImageServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookServiceExtended {
    private final BookRepository bookRepository;
    private final BookMainMapperDto bookMainMapperDto;
    private final BookMapperDto bookMapperDto;
    private final ImageServiceImp imageServiceImp;
    private final BookRatingRepository bookRatingRepository;

    @Override
    public Page<BookMainPageDto> getAllBook(Pageable pageable) {
        return getAllBookMainPage(bookRepository.findAll(pageable), pageable);

    }

    public BookDto getBookByBookId(Long bookId) {
        return bookRepository.findByBookID(bookId).map(bookMapperDto)
                .orElseThrow(() ->
                        new NoSuchElementException("No book found for bookId: " + bookId));
    }

    @Override
    public Page<BookMainPageDto> getAllBookMainPage(Page<Book> book, Pageable pageable) {
        List<BookMainPageDto> bookMainPageDto = book
                .stream()
                .map(bookMainMapperDto)
                .toList();

        if (bookMainPageDto.isEmpty()) {
            throw new NullPointerException("Books not found");
        }
        return new PageImpl<>(bookMainPageDto, pageable, bookMainPageDto.size());

    }

    @Override
    public Page<BookMainPageDto> findByCategoryName(String categoryName, Pageable pageable) {
        return getAllBookMainPage(bookRepository
                .findAllByCategoryName(categoryName, pageable), pageable);
    }
    @Override
    public Page<BookMainPageDto> findByAuthorName(String authorName, Pageable pageable) {
        String[] authorNames = authorName.split(" ");
        try {
            String firstName = authorNames[0];
            String lastName = authorNames[1];
            return getAllBookMainPage(bookRepository.findAllByAuthorName(firstName, lastName, pageable), pageable);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDataException("Invalid author name format");
        }
    }


    @Override
    public void uploadBookPhotos(Collection<MultipartFile> files, Long bookID) {
        Book book = bookRepository.findByBookID(bookID)
                .orElseThrow(() -> new NoSuchElementException("No book found for bookId: " + bookID));
        Collection<BookImage> bookImages = imageServiceImp.uploadBookPhotos(files, book);
        book.setImages(bookImages);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void addBookRating(Long bookID, Double score) {
        Book book = bookRepository.findByBookID(bookID)
                .orElseThrow(() -> new NoSuchElementException("No book found for bookId: " + bookID));
        if (score < 0 || score > 5) {
            throw new InvalidDataException("Score must be between 0 and 5");
        }
        BookRating rating = book.getRating() == null ? new BookRating() : book.getRating();
        rating.setCount(rating.getCount() + 1);
        rating.setRating(rating.getRating() + score);
        book.setRating(rating);
        bookRatingRepository.save(rating);
    }


}



