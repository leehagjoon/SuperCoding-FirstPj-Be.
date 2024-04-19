package com.firstpj.member.service.impl;

import com.firstpj.jpa.repository.CommentsRpository;
import com.firstpj.member.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentsRpository commentRepository;
//    public List<CommentsRqModel> findAllComments() {
//        List<CommentsEntity> commentsRqModels = commentRepository.findAllComments();
//        return commentsRqModels.stream().map(commentsEntity -> new CommentsRqModel(
//                commentsEntity.getCommentsId(),
//                commentsEntity.getPost(),
//                commentsEntity.getContent(),
//                commentsEntity.getMember(),
//                commentsEntity.getCreateAt())).collect(Collectors.toList());
//    }
}
