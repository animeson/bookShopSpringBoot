package com.svistun.bookshoop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ImageDTO(
        String filename,
        String url,
        @JsonIgnore
        String path
) {
}
