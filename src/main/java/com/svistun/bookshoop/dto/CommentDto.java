package com.svistun.bookshoop.dto;

import com.svistun.bookshoop.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record CommentDto(
        Long commentID,
        String comment,
        UserCommentDto user,
        LocalDateTime dateTimeComment

) {
}
