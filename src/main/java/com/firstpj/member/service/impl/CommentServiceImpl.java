package com.firstpj.member.service.impl;

import com.firstpj.jpa.entity.CommentsEntity;
import com.firstpj.jpa.repository.CommentRepository;
import com.firstpj.member.model.CommentsRqModel;
import com.firstpj.member.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    public List<CommentsRqModel> findAllComments() {
        List<CommentsEntity> commentsRqModels = commentRepository.findAllComments();
        return commentsRqModels.stream().map(commentsEntity -> new CommentsRqModel(
                commentsEntity.getCommentsId(),
                commentsEntity.getPost(),
                commentsEntity.getContent(),
                commentsEntity.getMember(),
                commentsEntity.getCreateAt())).collect(Collectors.toList());
    }
}
