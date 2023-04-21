package com.svistun.bookshoop.mapperDto;

import com.svistun.bookshoop.dto.CommentDto;
import com.svistun.bookshoop.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;
@Service
@RequiredArgsConstructor

public class CommentMapperDto implements Function<Comment, CommentDto> {
    private final UserAbbreviatedMapperDto userAbbreviatedMapperDTO;
    private final DateTimeFormatter dateFormat = DateTimeFormatter
            .ofPattern("dd.MM.yyyy : hh.mm a");
    @Override
    public CommentDto apply(Comment comment) {
        return new CommentDto(
                comment.getCommentID(),
                comment.getComment(),
                userAbbreviatedMapperDTO.apply(comment.getUser()),
                comment.getDateTimeComment().format(dateFormat));
    }
}
