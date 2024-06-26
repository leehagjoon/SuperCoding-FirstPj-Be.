package com.firstpj.member.service;

import com.firstpj.comments.model.CommentsBody;
import com.firstpj.jpa.entity.RoleType;
import com.firstpj.member.model.*;
import com.firstpj.post.model.PostsBody;
import jakarta.servlet.http.HttpServletRequest;

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







}
