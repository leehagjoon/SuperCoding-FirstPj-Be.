package com.firstpj.post.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostsBody {
    private String title;
    private String content;
}
