package com.svistun.bookshoop.dto;

public record CommentDto(
        Long commentID,
        String comment,
        UserAbbreviatedDto user,
        String dateTimeComment

) {
}
