package com.firstpj.post.model;

import com.firstpj.jpa.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CreatePostRp {
    //강의보고 수정
    private Integer postId;
    private Integer memberId;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createAt;

    public static CreatePostRp from(PostEntity postEntity){
        return CreatePostRp.builder()
                .postId(postEntity.getPostId())
                .memberId(postEntity.getMember().getMemberId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .author(postEntity.getAuthor())
                .createAt(postEntity.getCreateAt())
                .build();
    }
}
