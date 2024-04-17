package com.firstpj.member.service.impl;

import com.firstpj.jpa.entity.MemberEntity;
import com.firstpj.jpa.entity.RoleType;
import com.firstpj.jpa.repository.MemberRepository;
import com.firstpj.member.model.MemberSignUp;
import com.firstpj.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    @Override
    @Transactional(transactionManager = "tmJpa")
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
