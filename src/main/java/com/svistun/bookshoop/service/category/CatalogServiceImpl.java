package com.svistun.bookshoop.service.category;

import com.svistun.bookshoop.dto.AuthorDto;
import com.svistun.bookshoop.dto.BookDto;
import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.dto.CategoryDto;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.Category;
import com.svistun.bookshoop.mapperDto.BookMainMapperDto;
import com.svistun.bookshoop.mapperDto.BookMapperDto;
import com.svistun.bookshoop.repository.BookRepository;
import com.svistun.bookshoop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final BookMapperDto bookMapperDto;
    private final BookMainMapperDto bookMainMapperDto;

    @Override
    public Map<String, List<?>> getMainPageCatalog() {
        List<CategoryDto> categoryDto = categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryDto(
                        category.getCategoryID(),
                        category.getName(),
                        category.getBooks().size())).toList();

        List<BookMainPageDto> bookMainPageDtoList = bookRepository.findAll()
                .stream()
                .map(bookMainMapperDto).toList();
        Map<String, List<?>> response = new HashMap<>();
        response.put("category's", categoryDto);
        response.put("books", bookMainPageDtoList);
        if (response.values().stream().allMatch(Objects::isNull)) {
            throw new RuntimeException("Error: response is null");
        }
        return response;
    }

    @Override
    public BookDto getBookByBookId(Long bookId) {
        return bookRepository.findByBookID(bookId)
                .map(bookMapperDto)
                .orElseThrow(() -> new IllegalArgumentException("No book for the given id = " + bookId));
    }

    @Override
    public List<BookMainPageDto> getBookByCategoryName(String categoryName) {
        List<BookMainPageDto> bookMainPageDtoList = categoryRepository.findByName(categoryName)
                .stream()
                .flatMap(c -> c.getBooks().stream())
                .map(bookMainMapperDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (bookMainPageDtoList.isEmpty()) {
            throw new RuntimeException("No books found for the given category");
        }
        return bookMainPageDtoList;




    }
}
