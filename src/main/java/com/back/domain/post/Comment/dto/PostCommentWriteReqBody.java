package com.back.domain.post.Comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostCommentWriteReqBody(
        @NotBlank
        @Size(min = 2, max = 100)
        String content
) {
}