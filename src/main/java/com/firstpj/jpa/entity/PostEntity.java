package com.firstpj.jpa.entity;

import com.firstpj.post.model.CreatePostDto;
import com.firstpj.post.model.PostsBody;
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
@Table(name = "post")
public class PostEntity {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "author", length = 10, nullable = false)
    private String author;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;


    public void setPostsBody(PostsBody postsBody) {
        this.title = postsBody.getTitle();
        this.content = postsBody.getContent();
    }
    public static PostEntity toEntity(CreatePostDto createPostDto){
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(createPostDto.getTitle());
        postEntity.setContent(createPostDto.getContent());
        postEntity.setAuthor(createPostDto.getAuthor());
        return postEntity;

    }
}
