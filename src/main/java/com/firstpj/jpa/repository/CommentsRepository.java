package com.firstpj.jpa.repository;

import com.firstpj.jpa.entity.Comments;
import com.firstpj.jpa.entity.CommentsEntity;
import com.firstpj.jpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity,Integer> {


}
