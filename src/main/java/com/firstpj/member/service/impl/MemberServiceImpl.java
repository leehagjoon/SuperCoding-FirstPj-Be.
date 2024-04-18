package com.firstpj.member.service.impl;

import com.firstpj.jpa.entity.CommentsEntity;
import com.firstpj.jpa.entity.MemberEntity;
import com.firstpj.jpa.entity.PostEntity;
import com.firstpj.jpa.repository.CommentsRpository;
import com.firstpj.jpa.repository.MemberRepository;
import com.firstpj.jpa.repository.PostRepository;
import com.firstpj.member.model.*;
import com.firstpj.member.service.MemberService;
import com.firstpj.member.service.exceptions.NotFoundException;
import com.firstpj.member.service.mapper.CommentMapper;
import com.firstpj.member.service.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    private final PostRepository postRepository;

    private final CommentsRpository commentsRpository;

    @Override
    public PostRqModel updatePosts(String id, PostsBody postsBody) {
        Integer idInt = Integer.valueOf(id);
        PostEntity postEntityUpdated = postRepository.findById(idInt)
                .orElseThrow(() -> new NotFoundException("해당 게시물을 찾을 수 없습니다."));

        postEntityUpdated.setPostsBody(postsBody);

        return PostMapper.INSTANCE.postEntityToPostRqModel(postEntityUpdated);
    }

    @Override
    public CommentsRqModel updateComments(String id, CommentsBody commentsBody) {
        Integer idInt = Integer.valueOf(id);
        CommentsEntity commentsEntity = commentsRpository.findById(idInt)
                .orElseThrow(() -> new NotFoundException("해당 댓글을 찾을 수 없습니다."));

        commentsEntity.setCommentsBody(commentsBody);

        return CommentMapper.INSTANCE.commentsEntityToCommentsRqModel(commentsEntity);
    }


    @Override
    @CacheEvict(value = "comments",allEntries = true)
    public void deleteById(String id) {
        Integer idInt=Integer.parseInt(id);
        memberRepository.deleteById(idInt);
    }

    @Override
    public boolean signup(MemberSignUp memberSignUp) {
        String email = memberSignUp.getEmail();
        String password = memberSignUp.getPassword();
        String author = memberSignUp.getAuthor();

        // 유효성 검사
        if( email == null || email.isEmpty()){
            return false; // 이름이 없으면 회원가입 실패
        }

        //비밀번호 암호화
        String EncoderPassword = passwordEncoder.encode(password);

        // 유저가 있으면 ID만 등록 아니면 유저도 만들기
        MemberEntity member = memberRepository.findByEmail(email).orElseGet(() ->
                memberRepository.save(MemberEntity.builder()
                        .email(email)
                        .password(EncoderPassword)
                        .author(author)
                        .build())
        );
        return true;
    }

}
