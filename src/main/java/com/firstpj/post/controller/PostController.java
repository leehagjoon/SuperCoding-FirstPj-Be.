package com.firstpj.post.controller;

import com.firstpj.jpa.repository.PostRepository;
import com.firstpj.post.model.CreateCommentDto;
import com.firstpj.post.model.CreatePostDto;
import com.firstpj.post.model.PostRsModel;
import com.firstpj.post.model.PostsBody;
import com.firstpj.post.service.PostService;
import com.firstpj.post.service.impl.PostServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    @Autowired
    private final PostServiceImpl postServiceImpl;

    //게시물 전체 조회
    @GetMapping("/posts")
    public ResponseEntity<?> findAllPosts(PostRsModel rs) {
      try{
          List<PostRsModel> models = postService.findAllPosts(rs);
          return ResponseEntity.ok(models);
      }catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
      }
    }
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
    @PostMapping("/posts")
//    public ResponseEntity<?> createPost(@RequestBody CreatePostDto createPostDto){
//        postServiceImpl.createPost(createPostDto);
//        return ResponseEntity.ok(Collections.singletonMap("message","게시물이 성공적으로 작성 되었습니다"));
    public String registerTitle(@RequestBody CreatePostDto  createPostDto){
            postServiceImpl.cratePost(createPostDto);
            return "게시물이 성공적으로 작성되었습니다.";
        }

    @PostMapping("/comments")
    public String registerComment(@RequestBody CreateCommentDto createCommentDto) {
        postServiceImpl.CreateComment(createCommentDto);
        return "댓글이 성공적으로 작성되었습니다.";
    }
}


