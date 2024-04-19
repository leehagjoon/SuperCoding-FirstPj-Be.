package com.firstpj.member.controller;

import com.firstpj.member.model.CommentsRqModel;
import com.firstpj.member.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
