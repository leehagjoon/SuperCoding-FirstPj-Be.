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
    private MemberEntity memberId;

}
