package com.firstpj.member.model;

import com.firstpj.jpa.entity.RoleType;
import lombok.*;

/**
 * packageName    : com.firstpj.member.model
 * fileName       : CustomUserInfoModel
 * author         : hagjoon
 * date           : 2024-04-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-18        hagjoon       최초 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomUserInfoModel extends MemberModel {

    private Integer memberId;

    private String email;

    private String author;

    private String password;

    private RoleType role;
}
