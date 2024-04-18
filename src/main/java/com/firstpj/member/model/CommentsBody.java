package com.firstpj.member.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsBody {
    private Integer commentsId;
    private String content;
}
