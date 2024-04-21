package com.firstpj.comments.model;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateCommentDto {
    private String content;
    private String author;
    private Integer postId;
}
