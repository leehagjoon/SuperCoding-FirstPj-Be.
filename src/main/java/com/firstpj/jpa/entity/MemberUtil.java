package com.firstpj.jpa.entity;

import ch.qos.logback.core.spi.ErrorCodes;
import com.firstpj.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtil {

    private final MemberRepository memberRepository;

    public String findCurrentMember(){
        MemberEntity member=memberRepository.findByEmail(SecurityUtil.getCurrentMemberEmail())
                .orElseThrow(()->new UsernameNotFoundException("유저 정보 없음"));
        String members= String.valueOf(member);
        return members;
    }
}
