package com.firstpj.jwt;

import com.firstpj.jpa.entity.MemberEntity;
import com.firstpj.jpa.repository.MemberRepository;
import com.firstpj.member.model.CustomUserInfoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.firstpj.jwt
 * fileName       : CustomUserDetailService
 * author         : hagjoon
 * date           : 2024-04-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-18        hagjoon       최초 생성
 */
@Primary
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다. 이메일 : "+ username));

        CustomUserDetails customUserDetails = CustomUserDetails.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .password(member.getPassword())
                .role(member.getRole())
                .build();
        return customUserDetails;
    }
}
