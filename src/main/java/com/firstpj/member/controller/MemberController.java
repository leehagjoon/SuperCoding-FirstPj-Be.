package com.firstpj.member.controller;

import com.firstpj.config.security.JwtUtil;
import com.firstpj.jpa.entity.RoleType;
import com.firstpj.member.model.*;
import com.firstpj.member.model.dto.CommentsBody2;
import com.firstpj.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
 * 2024-04-18
 */
@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PutMapping("/posts/{postId}/updates")
    public ResponseEntity<?> updatePostsByPathId(@PathVariable Integer postId, @RequestBody PostsBody postsBody, HttpServletRequest request) {
        try{
            memberService.updatePosts(postId, postsBody,request);
            return ResponseEntity.ok(Collections.singletonMap("message","게시글 수정에 성공하였습니다."));
        }catch (Exception e){
            log.error("기타 예외가 발생",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message",e.getMessage()));
        }

    }

    @PutMapping("/comments/{commentsId}/updates")
    public ResponseEntity<?> updateCommentsByPathId(@PathVariable Integer commentsId, @RequestBody CommentsBody commentsBody) {
        try {
            memberService.updateComments(commentsId, commentsBody);
            return ResponseEntity.ok(Collections.singletonMap("message","댓글 수정에 성공하였습니다."));
        } catch (Exception e) {
            log.error("기타 예외가 발생",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message",e.getMessage()));
        }
//        return memberService.updateComments(commentsId, commentsBody);
    }


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
    public ResponseEntity<?> signup(@RequestBody MemberSignUp memberSignUp, RoleType roleType) throws Exception{
        boolean isSuccess = memberService.signUp(memberSignUp,roleType);

        if (isSuccess) {
            return ResponseEntity.ok(Collections.singletonMap("message", "회원가입이 완료되었습니다."));
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "회원가입에 실패했습니다."));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRqModel model, HttpServletResponse httpServletResponse){
        String memberEmail = memberService.login(model);

        String token = jwtUtil.createToken(memberEmail);
        httpServletResponse.setHeader("X-AUTH-TOKEN",token);

        return ResponseEntity.ok(Collections.singletonMap("message","로그인이 완료되었습니다."));
    }


    @PostMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "로그아웃 되었습니다.";
    }
}
