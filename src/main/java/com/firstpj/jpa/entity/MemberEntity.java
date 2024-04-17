package com.firstpj.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * packageName    : com.firstpj.api.member.entity
 * fileName       : MemberEntity
 * author         : hagjoon
 * date           : 2024-04-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16        hagjoon       최초 생성
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "author")
    private String author;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleType role;

}
