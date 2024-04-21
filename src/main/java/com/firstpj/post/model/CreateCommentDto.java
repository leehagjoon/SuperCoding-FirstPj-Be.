package com.firstpj.post.model;

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
