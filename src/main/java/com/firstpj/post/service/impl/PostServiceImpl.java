package com.firstpj.post.service.impl;

import com.firstpj.jpa.entity.PostEntity;
import com.firstpj.jpa.repository.PostRepository;
import com.firstpj.member.service.Exceptions.NotFoundException;
import com.firstpj.post.model.PostsBody;
import com.firstpj.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
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

    @Override
    @Transactional
    public void updatePosts(Integer postId, PostsBody rq, HttpServletRequest request) throws IllegalAccessException {
//        MemberEntity member = getuserFromToken(request);
        PostEntity postEntityUpdated = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("해당 게시물을 찾을 수 없습니다."));

        postEntityUpdated.setPostsBody(rq);

        postRepository.save(postEntityUpdated);

    }

    @CacheEvict(value = "post",allEntries = true)
    public void deleteByIdPost(String id) {
        Integer idInt=Integer.parseInt(id);
        postRepository.deleteById(idInt);
    }
}