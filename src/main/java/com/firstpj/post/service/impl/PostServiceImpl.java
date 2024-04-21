package com.firstpj.post.service.impl;

import com.firstpj.jpa.entity.PostEntity;
import com.firstpj.jpa.repository.CommentsRpository;
import com.firstpj.jpa.repository.MemberRepository;
import com.firstpj.jpa.repository.PostRepository;
import com.firstpj.member.service.Exceptions.NotFoundException;
import com.firstpj.post.model.CreatePostDto;
import com.firstpj.post.model.PostRsModel;
import com.firstpj.post.model.PostsBody;
import com.firstpj.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
   @Autowired
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    //게시글 전체조회
    @Override
    public List<PostRsModel> findAllPosts(PostRsModel rs) {
       List<PostEntity> entities = postRepository.findAll();
       return entities.stream()
               .map(PostRsModel::new)
               .collect(Collectors.toList());
    }
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

    @CacheEvict(value = "post", allEntries = true)
    public void deleteByIdPost(String id) {
        Integer idInt = Integer.parseInt(id);
        postRepository.deleteById(idInt);
    }

     //게시물 생성
       //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       //String email = authentication.getName();
        public void cratePost(CreatePostDto createPostDto){
        PostEntity entity = PostEntity.toEntity(createPostDto);
        postRepository.save(entity);
    }
        }


