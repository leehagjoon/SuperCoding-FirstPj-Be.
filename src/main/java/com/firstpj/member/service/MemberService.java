package com.firstpj.member.service;

import com.firstpj.jpa.entity.RoleType;
import com.firstpj.member.model.CommentsBody;
import com.firstpj.member.model.LoginRqModel;
import com.firstpj.member.model.MemberSignUp;
import com.firstpj.member.model.PostsBody;
import com.firstpj.member.model.dto.PostRqModelDto;
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

    PostRqModelDto updatePosts(Integer postId, PostsBody rq, HttpServletRequest request) throws IllegalAccessException;

    void updateComments(Integer id, CommentsBody commentsBody);

    public boolean signUp(MemberSignUp memberSignUp, RoleType roleType) throws Exception;

    public String login(LoginRqModel model);


    public void deleteById(String id);

    public void deleteByIdComments(String id);

    public void deleteByIdPost(String id);
}
