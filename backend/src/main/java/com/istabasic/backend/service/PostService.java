package com.istabasic.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.istabasic.backend.model.Post;
import com.istabasic.backend.model.Profile;
import com.istabasic.backend.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    PostRepository PostRepository;

    // C
    public Post save(Post Post) {
        return PostRepository.save(Post);
    }

    // R
    public Optional<Post> findById(Long id) {
        return PostRepository.findById(id);
    }

    public Post findByUsername(String title, Profile profile) {
        Long profileId = profile.getId();
        Post Post = null;
        Optional<Post> optional = PostRepository.findByTitle(title, profileId);
        if (optional.isPresent()) {
            Post = optional.get();
        } else {
            throw new Error("Post not found");
        }
        return Post;

    }

    public Page<Post> findAll(Pageable pageable) {
        return PostRepository.findAll(pageable);
    }

    // U
    public Post update(Long id, Post Post) {
        Optional<Post> PostResult = PostRepository.findById(id);

        if (PostResult.isPresent()) {
            Post PostUpdate = PostResult.get();

            PostRepository.save(PostUpdate);
            return PostUpdate;
        } else {
            throw new Error("Post not found"); // TODO:GESTIONE ERRORI
        }

    }

    // D
    public void delete(Long id) {
        PostRepository.deleteById(id);
    }
}
