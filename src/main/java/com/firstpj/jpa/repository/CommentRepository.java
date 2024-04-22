package com.firstpj.jpa.repository;

import com.firstpj.jpa.entity.CommentsEntity;
import com.firstpj.jpa.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentsEntity, Integer> {

}
