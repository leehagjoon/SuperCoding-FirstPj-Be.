package com.firstpj.member.controller;

import com.firstpj.member.model.MemberSignUp;
import com.firstpj.member.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final MemberServiceImpl memberService;

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
    public ResponseEntity<?> signup(@RequestBody MemberSignUp memberSignUp){
        boolean isSuccess = memberService.signup(memberSignUp);

        if(isSuccess){
            return ResponseEntity.ok(Collections.singletonMap("message","회원가입이 완료되었습니다."));

        }else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message","회원가입에 실패하였습니다."));
        }
    }
}
