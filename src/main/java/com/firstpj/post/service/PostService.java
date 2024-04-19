package com.firstpj.post.service;

import com.firstpj.post.model.PostsBody;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PostService {

//    List<PostRqModel> findAllPosts();
//
//    List<PostRqModel> findPostsByEmail(String email);

    void updatePosts(Integer postId, PostsBody rq, HttpServletRequest request) throws IllegalAccessException;

    public void deleteByIdPost(String id);
}
