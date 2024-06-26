package com.firstpj.comments.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.firstpj.jpa.entity.CommentsEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@JsonNaming(PropertyNamingStrategy.class)
public class CommentsRqModel {
    private Integer commentsId;
    private Integer postId;
    private Integer memberId;
    private String content;
    private String createdAt;

    private static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public CommentsRqModel(CommentsEntity commentsEntity) {
        this.commentsId=commentsEntity.getCommentsId();
        this.postId=commentsEntity.getPost().getPostId();
        this.memberId=commentsEntity.getMember().getMemberId();
        this.content=commentsEntity.getContent();
        this.createdAt=commentsEntity.getCreateAt().format(formatter);
    }
}
