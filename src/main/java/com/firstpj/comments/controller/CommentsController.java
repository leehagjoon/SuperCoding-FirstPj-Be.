package com.firstpj.comments.controller;

import com.firstpj.comments.model.CommentsBody;
import com.firstpj.comments.model.CreateCommentDto;
import com.firstpj.comments.service.CommentService;
import com.firstpj.comments.service.impl.CommentServiceImpl;
import com.firstpj.jpa.repository.CommentsRpository;
import com.firstpj.post.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.Collections;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentsController {

    private CommentService commentService;
    @Autowired
    private  final CommentServiceImpl commentServiceImpl;


    //댓글 조회
//    @GetMapping("/comments")
//    public ResponseEntity<?> findAllComments() {
//        List<CommentsRqModel> comments = commentService.findAllComments();
//        return ResponseEntity.ok().body(Map.of("comments", comments));
//
//    }
    @PostMapping("/comments")
    public String registerComment(@RequestBody CreateCommentDto createCommentDto) {
        commentServiceImpl.CreateComment(createCommentDto);
        return "댓글이 성공적으로 작성되었습니다.";
    }
    @PutMapping("/comments/{commentsId}/updates")
    public ResponseEntity<?> updateCommentsByPathId(@PathVariable Integer commentsId, @RequestBody CommentsBody commentsBody) {
        try {
            commentService.updateComments(commentsId, commentsBody);
            return ResponseEntity.ok(Collections.singletonMap("message","댓글 수정에 성공하였습니다."));
        } catch (Exception e) {
            log.error("기타 예외가 발생",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message",e.getMessage()));
        }
//        return memberService.updateComments(commentsId, commentsBody);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteCommentsByPathId(@PathVariable String id){
        commentService.deleteByIdComments(id);
    }
}




