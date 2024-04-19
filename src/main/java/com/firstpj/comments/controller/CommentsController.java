package com.firstpj.comments.controller;

import com.firstpj.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentsController {

    private CommentService commentService;

    //댓글 조회
//    @GetMapping("/comments")
//    public ResponseEntity<?> findAllComments() {
//        List<CommentsRqModel> comments = commentService.findAllComments();
//        return ResponseEntity.ok().body(Map.of("comments", comments));
//
//    }
}
