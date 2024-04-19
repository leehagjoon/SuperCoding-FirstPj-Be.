package com.firstpj.post.controller;

import com.firstpj.post.model.PostsBody;
import com.firstpj.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
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

    @PutMapping("/posts/{postId}/updates")
    public ResponseEntity<?> updatePostsByPathId(@PathVariable Integer postId, @RequestBody PostsBody postsBody, HttpServletRequest request) {
        try{
            postService.updatePosts(postId, postsBody,request);
            return ResponseEntity.ok(Collections.singletonMap("message","게시글 수정에 성공하였습니다."));
        }catch (Exception e){
            log.error("기타 예외가 발생",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message",e.getMessage()));
        }

    }

    @DeleteMapping("/post/{id}")
    public void deletePostByPathId(@PathVariable String id){
        postService.deleteByIdPost(id);
    }


}
