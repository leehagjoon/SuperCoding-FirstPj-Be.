package com.firstpj.member.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.firstpj.member.model
 * fileName       : LoginRqModel
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
public class LoginRqModel {

    private String email;

    private String password;
}
