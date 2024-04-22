package com.firstpj.comments.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.firstpj.jpa.entity.CommentsEntity;
import com.firstpj.jpa.entity.MemberEntity;
import com.firstpj.jpa.entity.PostEntity;
<<<<<<< HEAD:src/main/java/com/firstpj/comments/model/CommentsRqModel.java
import lombok.*;
=======
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
>>>>>>> a5c573a56ce8e79bbc79cdf215b21323d472e418:src/main/java/com/firstpj/member/model/CommentsRqModel.java

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@JsonNaming(PropertyNamingStrategy.class)
@NoArgsConstructor
@ToString
@Setter
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

<<<<<<< HEAD:src/main/java/com/firstpj/comments/model/CommentsRqModel.java
    public CommentsRqModel(Integer commentsId, PostEntity post, String content, MemberEntity member, java.time.LocalDateTime createAt) {
=======
    public CommentsRqModel(Integer commentsId, PostEntity post, String content, MemberEntity member, LocalDateTime createAt) {
>>>>>>> a5c573a56ce8e79bbc79cdf215b21323d472e418:src/main/java/com/firstpj/member/model/CommentsRqModel.java
    }
}
