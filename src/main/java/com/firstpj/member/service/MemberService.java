package com.firstpj.member.service;

import com.firstpj.config.security.JwtUtil;
import com.firstpj.jpa.entity.RoleType;
import com.firstpj.member.model.LoginRqModel;
import com.firstpj.member.model.MemberSignUp;

/**
 * packageName    : com.firstpj.api.member.service
 * fileName       : MemberService
 * author         : hagjoon
 * date           : 2024-04-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16        hagjoon       최초 생성
 */
public interface MemberService {

    public boolean signUp(MemberSignUp memberSignUp, RoleType roleType) throws Exception;

    public String login(LoginRqModel model);


    public void deleteById(String id);

    public String deleteByIdComments(String id,String token);

    public void deleteByIdPost(String id);
}
