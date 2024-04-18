package com.firstpj.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comments {

    @Id@Column(name = "comments_id")@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberId;

    @Column(name = "content",length = 255)
    private String content;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

}
