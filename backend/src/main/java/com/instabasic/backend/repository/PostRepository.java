package com.instabasic.backend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.instabasic.backend.model.Post;
import com.instabasic.backend.model.Profile;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByTitle(String title, Pageable pageable);

    Optional<Post> findByTitleAndProfile(String title, Profile profile);

    Page<Post> findAllByProfile_Profilename(String profilename, Pageable pageable);

    @Query("SELECT p FROM Post p JOIN p.profile pr JOIN pr.followers f WHERE f.profile.id = :profileid ORDER BY p.createdAt DESC")
    Page<Post> findPostsFromFollowedProfilesOrderByCreatedAtDesc(Long profileid, Pageable pageable);
}
