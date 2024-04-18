package com.firstpj.jpa.repository;

import com.firstpj.jpa.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    List<PostEntity> findByUserEmail(String email);
}
