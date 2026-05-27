package com.back.domain.post.Comment.dto;


import com.back.domain.post.Comment.entity.PostComment;

import java.time.LocalDateTime;

public record PostCommentDto (
        long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String content
) {
    public PostCommentDto(PostComment postComment) {
        this(
                postComment.getId(),
                postComment.getCreateDate(),
                postComment.getModifyDate(),
                postComment.getContent()
        );
    }
}