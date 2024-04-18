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
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class MemberEntity {
    @Id@Column(name = "member_id")@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @Column(name = "email",length = 25)
    private String email;

    @Column(name = "password",length = 255)
    private String password;

    @Column(name = "author",length = 10)
    private String author;

    @Column(name = "role",length = 30)
    private String role;


}
