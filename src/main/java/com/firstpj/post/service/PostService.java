package com.firstpj.post.service;

import com.firstpj.post.model.CreatePostDto;
import com.firstpj.post.model.PostRqModel;
import com.firstpj.post.model.PostRsModel;
import com.firstpj.post.model.PostsBody;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PostService {

    List<PostRsModel> findAllPosts(PostRsModel rs);
//
//    List<PostRqModel> findPostsByEmail(String email);

    void updatePosts(Integer postId, PostsBody rq, HttpServletRequest request) throws IllegalAccessException;

    public void deleteByIdPost(String id);

    void cratePost(CreatePostDto createPostDto);
}
