package com.firstpj.member.service;

import com.firstpj.member.model.PostRqModel;

import java.util.List;

public interface PostService {

    List<PostRqModel> findAllPosts();

    List<PostRqModel> findPostsByEmail(String email);
}
