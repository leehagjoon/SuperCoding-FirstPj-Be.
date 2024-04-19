package com.firstpj.member.controller;

import com.firstpj.member.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    //게시물 전체 조회
//    @GetMapping("/posts")
//    public ResponseEntity<?> findAllPosts() {
//
//        List<PostRqModel> posts = postService.findAllPosts();
//        return ResponseEntity.ok().body(Map.of("posts",posts));
//    }
//
//    //이메일을 통해 특정 게시물 검색
//    @GetMapping("/posts/search")
//    public ResponseEntity<?> findPostsByEmail(@RequestParam("author_email")String email) {
//        List<PostRqModel> posts = postService.findPostsByEmail(email);
//        return ResponseEntity.ok().body(Map.of("posts", posts));
//    }


}
