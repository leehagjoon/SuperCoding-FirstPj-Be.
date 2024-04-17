package com.firstpj.member.service.impl;

import com.firstpj.jpa.entity.MemberEntity;
import com.firstpj.jpa.entity.RoleType;
import com.firstpj.jpa.repository.MemberRepository;
import com.firstpj.config.security.JwtUtil;
import com.firstpj.member.model.LoginRqModel;
import com.firstpj.member.model.MemberSignUp;
import com.firstpj.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.List;

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

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;


    @Override
    @CacheEvict(value = "comments",allEntries = true)
    public void deleteById(String id) {
        Integer idInt=Integer.parseInt(id);
        memberRepository.deleteById(idInt);
    }

    @Override
    public boolean signUp(MemberSignUp memberSignUp) throws Exception {
       String email = memberSignUp.getEmail();
       String password = memberSignUp.getPassword();
       String author = memberSignUp.getAuthor();

       if(email == null || email.isEmpty()){
           return false;
       }

       String encodedPassword = passwordEncoder.encode(password);

       MemberEntity member = memberRepository.findByEmail(email).orElseGet(()->
               memberRepository.save(MemberEntity.builder()
                       .email(email)
                       .password(encodedPassword)
                               .author(author)
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

    public String createToken(String email){
        String token = jwtUtil.createToken(email);
        return token;
    }


}
