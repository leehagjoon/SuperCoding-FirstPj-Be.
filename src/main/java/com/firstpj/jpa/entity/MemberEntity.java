package com.firstpj.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberId;

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
