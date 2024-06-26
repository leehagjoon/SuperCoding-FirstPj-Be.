package com.firstpj.member.service.impl;

import com.firstpj.comments.model.CommentsBody;
import com.firstpj.jpa.entity.CommentsEntity;
import com.firstpj.jpa.entity.MemberEntity;
import com.firstpj.jpa.entity.PostEntity;
import com.firstpj.jpa.entity.RoleType;
import com.firstpj.jpa.repository.CommentsRpository;
import com.firstpj.jpa.repository.MemberRepository;
import com.firstpj.config.security.JwtUtil;
import com.firstpj.jpa.repository.PostRepository;
import com.firstpj.member.model.*;


import com.firstpj.member.service.MemberService;
import com.firstpj.post.model.PostsBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

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
    private final CommentsRpository commentsRepository;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final PostRepository postRepository;




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
//        String email = model.getEmail();
//        String password = model.getPassword();

        try{
            MemberEntity member = memberRepository.findByEmail(model.getEmail())
                    .orElseThrow(()-> new IllegalArgumentException("가입되지 않은 Email 입니다."));
            if(!passwordEncoder.matches(model.getPassword(), member.getPassword())){
                throw new IllegalAccessException("잘못된 비밀번호 입니다.");
            }
            return jwtUtil.createToken(member.getEmail());
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(email,password));
//            SecurityContextHolder.getContext()
//                    .setAuthentication(authentication);
//            MemberEntity memberEntity = memberRepository.findByEmail(email)
//                    .orElseThrow(()-> new UsernameNotFoundException("user이름을 찾을 수 없습니다."));
//
//             return jwtUtil.createToken(email);
        }catch (Exception e){
            e.printStackTrace();
            throw new NotAcceptableStatusException("로그인 할수 없습니다.");
        }
    }

//    private MemberEntity getuserFromToken(HttpServletRequest request) throws IllegalAccessException {
//        String token = jwtUtil.resolveToken(request);
//        Claims claims;
//        if(StringUtils.hasText(token)){
//            if(jwtUtil.validateToken(token)){
//                claims = jwtUtil.getUserInfoFromToken(token);
//            }else{
//                throw new IllegalAccessException("올바른 token 이 아닙니다.");
//            }
//        return memberRepository.findByEmail(claims.getSubject()).orElseThrow(()-> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
//        }
//        throw new IllegalAccessException("token이 없습니다.");
//    }


}
