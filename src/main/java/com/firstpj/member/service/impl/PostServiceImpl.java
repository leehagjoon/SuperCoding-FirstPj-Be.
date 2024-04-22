package com.firstpj.member.service.impl;

import com.firstpj.jpa.entity.PostEntity;
import com.firstpj.jpa.repository.PostRepository;
import com.firstpj.member.model.PostRqModel;
import com.firstpj.member.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    //게시글 전체조회
    @Override
    public List<PostRqModel> findAllPosts() {
        List<PostEntity> postList = postRepository.findAll();
        return postList.stream().map(postEntity -> new PostRqModel(
                postEntity.getPostId(),
                postEntity.getMember(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getAuthor(),
                postEntity.getCreateAt())).collect(Collectors.toList());
    }

    public List<PostRqModel> findPostsByEmail(String email) {
        List<PostEntity> postEntities = postRepository.findByUserEmail(email);
        return postEntities.stream().map(postEntity -> new PostRqModel(
                postEntity.getPostId(),
                postEntity.getMember(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getAuthor(),
                postEntity.getCreateAt())).collect(Collectors.toList());
    }
}
