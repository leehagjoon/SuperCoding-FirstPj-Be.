package com.firstpj.member.service.impl;

import com.firstpj.jpa.entity.*;
import com.firstpj.jpa.repository.CommentsRepository;
import com.firstpj.jpa.repository.MemberRepository;
import com.firstpj.config.security.JwtUtil;
import com.firstpj.jpa.repository.PostRepository;
import com.firstpj.member.model.CustomUserInfoModel;
import com.firstpj.member.model.LoginRqModel;
import com.firstpj.member.model.MemberSignUp;
import com.firstpj.member.service.Exceptions.NotFoundException;
import com.firstpj.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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

    private final CommentsRepository commentsRepository;

    private final PostRepository postRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final MemberUtil memberUtil;

    @CacheEvict(value = "comments",allEntries = true)
    public String deleteByIdComments(String id) {

        Integer idInt=Integer.parseInt(id);
//
//        CommentsEntity comments =commentsRepository.findById(idInt)
//                .orElseThrow(()-> new NotFoundException("해당 id 가 없음 "));
//
//        Integer memberId=comments.getMember().getMemberId();
//
//
//        MemberEntity memberEntity = memberRepository.findByEmail(memberUtil.findCurrentMember())
//                        .orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
//
//        if (memberEntity.getMemberId().equals(memberId)){
//            commentsRepository.deleteById(idInt);
//            return "삭제 되었습니다.";
//        }else {
//            return "본인 글만 삭제 할수 있습니다.";
//        }
      commentsRepository.deleteById(idInt);
      return "삭제되었습니다.";
    }

    @CacheEvict(value = "post",allEntries = true)
    public void deleteByIdPost(String id) {
        Integer idInt=Integer.parseInt(id);
        postRepository.deleteById(idInt);
    }


    @Override
    public boolean signUp(MemberSignUp memberSignUp, RoleType roleType) throws Exception {
        memberSignUp.setRole(RoleType.USER);

       String email = memberSignUp.getEmail();
       String password = memberSignUp.getPassword();
       String author = memberSignUp.getAuthor();
       RoleType role = memberSignUp.getRole();


       if(email == null || email.isEmpty()){
           return false;
       }

       String encodedPassword = passwordEncoder.encode(password);

       MemberEntity member = memberRepository.findByEmail(email).orElseGet(()->
               memberRepository.save(MemberEntity.builder()
                       .email(email)
                       .password(encodedPassword)
                               .author(author)
                               .role(role)
                       .build()));
       return true;
    }

    @Override
    public String login(LoginRqModel model) {
        String email = model.getEmail();
        String password = model.getPassword();

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,password));
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            MemberEntity memberEntity = memberRepository.findByEmail(email)
                    .orElseThrow(()-> new UsernameNotFoundException("user이름을 찾을 수 없습니다."));

             return jwtUtil.createToken(email);
        }catch (Exception e){
            e.printStackTrace();
            throw new NotAcceptableStatusException("로그인 할수 없습니다.");
        }
    }

    @Override
    public void deleteById(String id) {

    }


}
