package com.firstpj.member.model;

import com.firstpj.jpa.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUp {

    private String email;

    private String password;

}
