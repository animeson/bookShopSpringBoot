package com.svistun.bookshoop.service.book;

import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.mapperDto.BookMainMapperDto;
import com.svistun.bookshoop.mapperDto.BookMapperDto;
import com.svistun.bookshoop.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMainMapperDto bookMainMapperDto;
    private final BookMapperDto bookMapperDto;

    @Override
        public Page<BookMainPageDto> getAllBook(Pageable pageable) {
        return getAllBookMainPage(bookRepository.findAll(pageable), pageable);

    }

    @Override
    public BookDto getBookByBookId(Long bookId) {
        return bookRepository.findByBookID(bookId)
                .map(bookMapperDto)
                .orElseThrow(() -> new NullPointerException("No book for the given id = " +
                        bookId + " or book deleted"));
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
        return new PageImpl<>(bookMainPageDto, pageable,bookMainPageDto.size());

    }

    @Override
    public Page<BookMainPageDto> findByCategoryName(String categoryName, Pageable pageable) {
        return getAllBookMainPage(bookRepository
                .findAllByCategoryName(categoryName, pageable), pageable);
    }


}
