package com.firstpj.comments.service;

import com.firstpj.comments.model.CommentsBody;
import com.firstpj.comments.model.CreateCommentDto;

public interface CommentService {
//    List<CommentsRqModel> findAllComments();

    void updateComments(Integer id, CommentsBody commentsBody);

    public void deleteById(String id);

    public void deleteByIdComments(String id);

    void CreateComment(CreateCommentDto createCommentDto);
}
