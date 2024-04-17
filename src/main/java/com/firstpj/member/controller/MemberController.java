package com.firstpj.member.controller;

import com.firstpj.member.model.MemberLoginModel;
import com.firstpj.member.model.MemberSignUp;
import com.firstpj.member.service.MemberService;
import com.firstpj.member.service.impl.MemberServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private JwtTokenProvider jwtTokenProvider;

    @DeleteMapping("/comments/{id}")
    public String deleteCommentsByPathId(@PathVariable String id){
        memberService.deleteById(id);
        return "댓글이 삭제되었습니다.";
    }

    @DeleteMapping("/post/{id}")
    public String deletePostByPathId(@PathVariable String id){
        memberService.deleteById(id);
        return "해당 글이 삭제 되었습니다.";
    }

    @PostMapping("/signup")
    public Integer signup(@RequestBody MemberSignUp memberSignUp) throws Exception{
        return memberService.signup(memberSignUp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginModel model, HttpServletResponse httpServletResponse){

        //로그인 시도
        String memberEmail = memberService.login(model);

        //토큰 생성
        String token = jwtTokenProvider.createToken(memberEmail);
        httpServletResponse.setHeader("Authorization","Bearer "+ token);

        return ResponseEntity.ok(Collections.singletonMap("message","로그인 성공"));
    }

    @PostMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "로그아웃 되었습니다.";
    }
}
