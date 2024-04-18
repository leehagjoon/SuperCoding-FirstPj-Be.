package com.firstpj.jpa.repository;

import com.firstpj.jpa.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<CommentsEntity,Integer > {
}
