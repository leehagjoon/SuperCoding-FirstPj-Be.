package com.firstpj.member.service.mapper;

import com.firstpj.jpa.entity.CommentsEntity;

import com.firstpj.member.model.CommentsRqModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    // 싱글톤
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    // PostEntity가 들어와서 PostRqModel이 나온다.
    CommentsRqModel commentsEntityToCommentsRqModel(CommentsEntity commentsEntity);
}
