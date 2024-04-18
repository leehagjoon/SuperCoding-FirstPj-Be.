package com.firstpj.member.model;

import com.firstpj.jpa.entity.MemberEntity;
import com.firstpj.jpa.entity.RoleType;
import lombok.*;

/**
 * packageName    : com.firstpj.member.model
 * fileName       : MemberSignUp
 * author         : hagjoon
 * date           : 2024-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-17        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUp {

    private String email;

    private String password;

    private String author;

    private RoleType role;


}
