package com.firstpj.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
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

    @Column(name = "content",length = 255)
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
