package com.svistun.bookshoop.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableFactory {
    public static Pageable create(int page, int size) {
        return PageRequest.of(page, size);
    }
}
