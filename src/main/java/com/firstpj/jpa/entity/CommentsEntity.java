package com.firstpj.jpa.entity;

import com.firstpj.comments.model.CommentsBody;
import com.firstpj.comments.model.CreateCommentDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@ToString
@Table(name = "comments")
public class CommentsEntity {

    @Id@Column(name = "comments_id")@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(name = "content",length = 255 )
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    public static CommentsEntity toSaveEntity(CreateCommentDto createCommentDto){
        CommentsEntity commentsEntity =new CommentsEntity();
        commentsEntity.setContent(createCommentDto.getContent());
        commentsEntity.setPost(commentsEntity.getPost());
        return commentsEntity;
    }

    public void setCommentsBody(CommentsBody commentsBody) {
        this.content = commentsBody.getContent();
    }

}




