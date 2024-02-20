package com.istabasic.backend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.istabasic.backend.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByTitle(String Title, Pageable pageable);

    Optional<Post> findByTitle(String Title, Long id);

}