package com.firstpj.comments.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsBody {
    private Integer commentsId;
    private String content;
}
