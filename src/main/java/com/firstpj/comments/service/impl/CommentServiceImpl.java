package com.firstpj.comments.service.impl;

import com.firstpj.comments.model.CommentsBody;
import com.firstpj.comments.model.CreateCommentDto;
import com.firstpj.jpa.entity.CommentsEntity;
import com.firstpj.jpa.repository.CommentsRpository;
import com.firstpj.comments.service.CommentService;
import com.firstpj.jpa.repository.PostRepository;
import com.firstpj.member.service.Exceptions.NotFoundException;
import com.firstpj.post.service.impl.PostServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentsRpository commentRepository;

//    public List<CommentsRqModel> findAllComments() {
//        List<CommentsEntity> commentsRqModels = commentRepository.findAllComments();
//        return commentsRqModels.stream().map(commentsEntity -> new CommentsRqModel(
//                commentsEntity.getCommentsId(),
//                commentsEntity.getPost(),
//                commentsEntity.getContent(),
//                commentsEntity.getMember(),
//                commentsEntity.getCreateAt())).collect(Collectors.toList());
//    }

    @Override
    @Transactional
    public void updateComments(Integer commentsId, CommentsBody commentsBody) {
//        Integer idInt = Integer.valueOf(id);
        CommentsEntity commentsEntityUpdated = commentRepository.findById(commentsId)
                .orElseThrow(() -> new NotFoundException("해당 댓글을 찾을 수 없습니다."));

        commentsEntityUpdated.setCommentsBody(commentsBody);

        commentRepository.save(commentsEntityUpdated);

//        return CommentMapper.INSTANCE.commentsEntityToCommentsRqModel(commentsEntityUpdated);
    }


    @CacheEvict(value = "comments", allEntries = true)
    public void deleteByIdComments(String id) {

        Integer idInt = Integer.parseInt(id);

//        CommentsEntity comments =commentsRepository.findById(idInt)
//                .orElseThrow(()-> new NotFoundException("해당 id 가 없음 "));
//
//        Integer memberId=comments.getMember().getMemberId();
//
//
//        MemberEntity memberEntity = memberRepository.findByEmail(memberUtil.findCurrentMember())
//                        .orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
//
//        if (memberEntity.getMemberId().equals(memberId)){
//            commentsRepository.deleteById(idInt);
//            return "삭제 되었습니다.";
//        }else {
//            return "본인 글만 삭제 할수 있습니다.";
//        }
        commentRepository.deleteById(idInt);
    }

    @Override
    @CacheEvict(value = "comments", allEntries = true)
    public void deleteById(String id) {
        Integer idInt = Integer.parseInt(id);
        commentRepository.deleteById(idInt);
    }

    //댓글생성
    public void CreateComment (CreateCommentDto createCommentDto) {
        CommentsEntity commentsEntity = CommentsEntity.toSaveEntity(createCommentDto);
        commentRepository.save(commentsEntity);
    }
}

