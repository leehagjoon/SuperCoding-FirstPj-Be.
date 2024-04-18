package com.firstpj.member.service.impl;

import com.firstpj.jpa.repository.CommentsRepository;
import com.firstpj.jpa.repository.MemberRepository;
import com.firstpj.jpa.repository.PostRepository;
import com.firstpj.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.firstpj.api.member.service.impl
 * fileName       : MemberServiceImpl
 * author         : hagjoon
 * date           : 2024-04-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;


//    @CacheEvict(value = "comments",allEntries = true)
    public void deleteByIdComments(String id) {
        Integer idInt=Integer.parseInt(id);
        commentsRepository.deleteById(idInt);
    }

    public void deleteByIdPost(String id) {
        Integer idInt=Integer.parseInt(id);
        postRepository.deleteById(idInt);
    }



}
