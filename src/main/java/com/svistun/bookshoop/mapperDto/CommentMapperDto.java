package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.CommentDto;
import com.svistun.bookshoop.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
@RequiredArgsConstructor

public class CommentMapperDto implements Function<Comment, CommentDto> {
    private final CommentUserMapperDto commentUserMapperDTO;
    @Override
    public CommentDto apply(Comment comment) {
        return new CommentDto(
                comment.getCommentID(),
                comment.getComment(),
                commentUserMapperDTO.apply(comment.getUser()),
                comment.getDateTimeComment());
    }
}
