package com.firstpj.jpa.entity;

import com.firstpj.member.model.PostsBody;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class PostEntity {
    @Id@Column(name = "post_id")@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "title",length = 50)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "author", length = 10)
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

}
