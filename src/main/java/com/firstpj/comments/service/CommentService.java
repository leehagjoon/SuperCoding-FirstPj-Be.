package com.firstpj.comments.service;

import com.firstpj.comments.model.CommentsBody;

public interface CommentService {


    void updateComments(Integer id, CommentsBody commentsBody);

    public void deleteById(String id);

    public void deleteByIdComments(String id);
}
