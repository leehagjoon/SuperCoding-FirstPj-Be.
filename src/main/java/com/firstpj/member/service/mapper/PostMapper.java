package com.firstpj.member.service.mapper;

import com.firstpj.jpa.entity.PostEntity;
import com.firstpj.member.model.PostRqModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    // 싱글톤
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    // PostEntity가 들어와서 PostRqModel이 나온다.
    PostRqModel postEntityToPostRqModel(PostEntity postEntity);
}
