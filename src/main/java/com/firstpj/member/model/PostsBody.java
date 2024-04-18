package com.firstpj.member.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostsBody {
    private Integer postId;
    private String title;
    private String content;
}
