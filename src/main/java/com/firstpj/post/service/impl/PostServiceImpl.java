package com.firstpj.post.service.impl;

import com.firstpj.jpa.repository.PostRepository;
import com.firstpj.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    //게시글 전체조회
//    @Override
//    public List<PostRqModel> findAllPosts() {
//        List<PostEntity> postList = postRepository.findAll();
//        return postList.stream().map(postEntity -> new PostRqModel(
//                postEntity.getPostId(),
//                postEntity.getMember().getMemberId(),
//                postEntity.getTitle(),
//                postEntity.getContent(),
//                postEntity.getAuthor(),
//                postEntity.getCreateAt())
//    }
//
//    public List<PostRqModel> findPostsByEmail(String email) {
//        List<PostEntity> postEntities = postRepository.findByUserEmail(email);
//        return postEntities.stream().map(postEntity -> new PostRqModel(
//                postEntity.getPostId(),
//                postEntity.getMember(),
//                postEntity.getTitle(),
//                postEntity.getContent(),
//                postEntity.getAuthor(),
//                postEntity.getCreateAt())).collect(Collectors.toList());
//    }
}
