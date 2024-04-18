package com.firstpj.member.service;

import com.firstpj.member.model.*;

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

    public boolean signup(MemberSignUp memberSignUp);

    public void deleteById(String id);

    PostRqModel updatePosts(String id, PostsBody postsBody);

    CommentsRqModel updateComments(String id, CommentsBody commentsBody);
}
