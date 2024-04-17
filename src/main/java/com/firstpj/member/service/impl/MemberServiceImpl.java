package com.firstpj.member.service.impl;

import com.firstpj.jpa.entity.MemberEntity;
import com.firstpj.jpa.repository.MemberRepository;
import com.firstpj.member.model.MemberLoginModel;
import com.firstpj.member.model.MemberSignUp;
import com.firstpj.member.service.MemberService;
import jakarta.transaction.Transactional;
import jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private  JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;


    @Override
    @CacheEvict(value = "comments",allEntries = true)
    public void deleteById(String id) {
        Integer idInt=Integer.parseInt(id);
        memberRepository.deleteById(idInt);
    }

    @Override
    public Integer signup(MemberSignUp memberSignUp) throws Exception {
        if (memberRepository.findByEmail(memberSignUp.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        MemberEntity member = memberRepository.save(memberSignUp.toEntity());
        member.encodePassword(passwordEncoder);

        member.addUserAuthority();
        return member.getMemberId();
    }

    @Override
    public String login(MemberLoginModel model) {
        String email = model.getEmail();
        String password = model.getPassword();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.createToken(email);
    }

    public String createToken(String email){
        String token = jwtTokenProvider.createToken(email);
        return token;
    }
}
