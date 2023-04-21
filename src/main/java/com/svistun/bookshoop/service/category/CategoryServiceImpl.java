package com.svistun.bookshoop.service.category;

import com.svistun.bookshoop.dto.CategoryDto;
import com.svistun.bookshoop.mapperDto.CategoryMapperDto;
import com.svistun.bookshoop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapperDto categoryMapperDto;
    @Override
    public Collection<CategoryDto> getAllCategory() {
        Collection<CategoryDto> categoryDto = categoryRepository.findAll()
                .stream()
                .map(categoryMapperDto).toList();
        if (categoryDto.isEmpty()) {
            throw new NullPointerException("Category not found");
        }
        return categoryDto;
    }
}
