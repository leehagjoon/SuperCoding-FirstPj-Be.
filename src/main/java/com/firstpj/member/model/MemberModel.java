package com.firstpj.member.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.firstpj.jpa.entity.MemberEntity;
import com.firstpj.jpa.entity.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.firstpj.member.model
 * fileName       : MemberModel
 * author         : hagjoon
 * date           : 2024-04-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16        hagjoon       최초 생성
 */
@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.class)
public class MemberModel {
    private Integer memberId;
    private String email;
    private String password;
    private String author;
    private RoleType role;

    public MemberModel(MemberEntity memberEntity){
        this.memberId=memberEntity.getMemberId();
        this.email=memberEntity.getEmail();
        this.password=memberEntity.getPassword();
        this.author=memberEntity.getAuthor();
        this.role=memberEntity.getRole();
    }

}
