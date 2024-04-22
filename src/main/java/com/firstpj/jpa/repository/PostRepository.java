package com.firstpj.jpa.repository;

import com.firstpj.jpa.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

<<<<<<< HEAD
    List<PostEntity> findByEmail(String email);
=======
    List<PostEntity> findByUserEmail(String email);
>>>>>>> a5c573a56ce8e79bbc79cdf215b21323d472e418
}
