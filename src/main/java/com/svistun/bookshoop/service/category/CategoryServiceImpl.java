package com.svistun.bookshoop.service.category;

import com.svistun.bookshoop.dto.CategoryDto;
import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.Category;
import com.svistun.bookshoop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryDto> getAllCategory() {
        List<CategoryDto> categoryDto = categoryRepository.findAll()
                .stream()
                .map(response -> new CategoryDto(
                        response.getCategoryID(),
                        response.getName(),
                        response.getBooks().size())).toList();
        if (categoryDto.isEmpty()) {
            throw new NullPointerException("Category not found");
        }
        return categoryDto;
    }
}
