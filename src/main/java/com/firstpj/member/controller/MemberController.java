package com.firstpj.member.controller;

import com.firstpj.member.model.MemberSignUp;
import com.firstpj.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

/**
 * packageName    : com.firstpj.api.member.controller
 * fileName       : MemberController
 * author         : hagjoon
 * date           : 2024-04-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16        hagjoon       최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody MemberSignUp memberSignUp){
        boolean isSuccess = memberService.signup(memberSignUp);

        if(isSuccess){
            return ResponseEntity.ok(Collections.singletonMap("message","회원가입이 완료되었습니다."));

        }else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message","회원가입에 실패하였습니다."));
        }
    }
}
