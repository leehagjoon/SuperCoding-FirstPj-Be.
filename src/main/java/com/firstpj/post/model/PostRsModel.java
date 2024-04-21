package com.firstpj.post.model;

import com.firstpj.jpa.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.firstpj.post.model
 * fileName       : PostRsModel
 * author         : hagjoon
 * date           : 2024-04-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-21        hagjoon       최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRsModel {

    private Integer postId;

    private String title;

    private String content;

    private String author;

    public PostRsModel(PostEntity entity){
        this.postId = entity.getPostId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
