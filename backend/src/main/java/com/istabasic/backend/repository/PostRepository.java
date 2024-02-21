package com.istabasic.backend.repository;

import com.istabasic.backend.model.Post;
import com.istabasic.backend.model.Profile;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByTitle(String title, Pageable pageable);

    Optional<Post> findByTitleAndProfile(String title, Profile profile);

    Page<Post> findAllByProfile_Profilename(String profilename, Pageable pageable);

}