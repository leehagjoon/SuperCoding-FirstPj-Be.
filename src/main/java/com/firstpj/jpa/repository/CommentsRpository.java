package com.firstpj.jpa.repository;

import com.firstpj.jpa.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRpository extends JpaRepository<CommentsEntity, Integer> {
}
