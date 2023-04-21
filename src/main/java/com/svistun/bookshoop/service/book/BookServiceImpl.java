package com.svistun.bookshoop.service.book;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.BookImage;
import com.svistun.bookshoop.entity.BookRating;
import com.svistun.bookshoop.exception.InvalidDataException;
import com.svistun.bookshoop.mapperDto.BookMainMapperDto;
import com.svistun.bookshoop.mapperDto.BookMapperDto;
import com.svistun.bookshoop.repository.StockRepository;
import com.svistun.bookshoop.service.image.ImageServiceImp;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final ImageServiceImp imageServiceImp;
    private final StockRepository stockRepository;
    private final BookMainMapperDto bookMainMapperDto;
    private final BookMapperDto bookMapperDto;

    @Override
    public Book getBookId(Long bookId) {
        return stockRepository.findByBookId(bookId).orElseThrow(() ->
                new NoSuchElementException("No book found for bookId: " + bookId));
    }

    @Override
    @Transactional
    public void uploadBookPhotos(Collection<MultipartFile> files, Long bookID) {
        Book book = getBookId(bookID);
        Collection<BookImage> bookImages = imageServiceImp.uploadBookPhotos(files, book);
        book.setImages(bookImages);
    }

    /* TODO: Fix rating, create new table "rating" in db and add userID, data create rating, score and book */
    @Override
    @Transactional
    public void addBookRating(Long bookID, Double score) {
        Book book = getBookId(bookID);
        if (score < 0 || score > 5) {
            throw new InvalidDataException("Score must be between 0 and 5");
        }
        BookRating rating = book.getRating() == null ? new BookRating() : book.getRating();
        rating.setCount(rating.getCount() + 1);
        rating.setRating((rating.getRating() + score) / rating.getCount());
        book.setRating(rating);
    }

    @Override
    public BookDto getBookByBookId(Long bookId) {
        return stockRepository.findByBookId(bookId).map(bookMapperDto)
                .orElseThrow(() ->
                        new NoSuchElementException("No book found for bookId: " + bookId));
    }

    @Override
    public Page<BookMainPageDto> findByCategoryID(Long categoryID, Pageable pageable) {
        return conversionToBookMainPageDto(stockRepository
                .findBookByCategoryId(categoryID, pageable).toList(),pageable);
    }

    @Override
    public Page<BookMainPageDto> getAllBookMainPage(Pageable pageable) {
        return conversionToBookMainPageDto(stockRepository
                        .findAllByBook(pageable).stream().toList(), pageable);


    }

    @Override
    public Page<BookMainPageDto> findByAuthorID(Long authorID, Pageable pageable) {
        return conversionToBookMainPageDto(stockRepository
                .findBookByAuthorID(authorID, pageable).toList(), pageable);

    }


    @Contract("_, _ -> new")
    private @NotNull Page<BookMainPageDto> conversionToBookMainPageDto(@NotNull Collection<Book> books, Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable parameter cannot be null");
        }
        if (books.isEmpty()) {
            return Page.empty();
        }

        List<BookMainPageDto> bookMainPageDto = books
                .stream()
                .map(bookMainMapperDto)
                .collect(Collectors.toList());
        return new PageImpl<>(bookMainPageDto, pageable, bookMainPageDto.size());
    }



}



