package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.*;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryMapperDto implements Function<Category, CategoryDto> {

    @Override
    public CategoryDto apply(Category category) {
        return new CategoryDto(
                category.getCategoryID(),
                category.getName(),
                category.getBooks().size());
    }
}

