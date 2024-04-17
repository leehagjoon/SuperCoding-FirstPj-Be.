package com.firstpj.member.controller;

import com.firstpj.member.model.MemberSignUp;
import com.firstpj.member.service.MemberService;
import lombok.RequiredArgsConstructor;
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


    @PostMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "로그아웃 되었습니다.";
    }
}
