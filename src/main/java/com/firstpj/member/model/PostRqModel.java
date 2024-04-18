package com.firstpj.member.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.firstpj.jpa.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.class)
public class PostRqModel {
    private Integer postId;
    private Integer memberId;
    private String title;
    private String content;
    private String author;
    private String createAt;

    private static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @Builder
    public PostRqModel (PostEntity postEntity){
        this.postId=postEntity.getPostId();
        this.memberId=postEntity.getMember().getMemberId();
        this.title=postEntity.getTitle();
        this.content=postEntity.getContent();
        this.author=postEntity.getAuthor();
        this.createAt=postEntity.getCreateAt().format(formatter);
    }
}
