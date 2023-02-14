package com.svistun.bookshoop.response;

import com.svistun.bookshoop.dto.BookMainPageDto;
import com.svistun.bookshoop.dto.CategoryDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

import java.util.List;
@Data
@Accessors(chain = true)
public class MainPageResponse {
    private List<CategoryDto> category;
    private Page<BookMainPageDto> books;
}
