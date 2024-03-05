package com.instabasic.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.instabasic.backend.common.util.exception.ErrorHandler;
import com.instabasic.backend.model.Post;
import com.instabasic.backend.model.Profile;
import com.instabasic.backend.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    PostRepository PostRepository;

    // C
    public Post save(Post post) {
        if (post != null) {
            return PostRepository.save(post);
        } else {
            throw new ErrorHandler(400, "null");
        }
    }

    // R
    public Post findById(Long id) {
        if (id != null) {
            Optional<Post> optional = PostRepository.findById(id);

            if (optional.isPresent()) {
                return optional.get();
            } else {
                throw new ErrorHandler(404, "Post not found");
            }
        } else {
            throw new ErrorHandler(404, "Id null");
        }
    }

    public Post findByTitle(String title, Profile profile) {
        Post Post = null;
        Optional<Post> optional = PostRepository.findByTitleAndProfile(title, profile);
        if (optional.isPresent()) {
            Post = optional.get();
        } else {
            throw new ErrorHandler(400, "Post not found");
        }
        return Post;

    }

    public Page<Post> getPostsByProfileName(String profileName, Pageable pageable) {
        if (profileName == null) {
            return PostRepository.findAllByProfile_Profilename(profileName, pageable);
        } else {
            throw new ErrorHandler(400, "null");
        }

    }

    // U
    public Post update(Long id, Post PostUpdate) {
        if (id == null) {
            throw new ErrorHandler(400, "Post id is null");
        }
        Optional<Post> PostResult = PostRepository.findById(id);
        if (!PostResult.isPresent()) {
            throw new ErrorHandler(404, "Post not found");
        }
        Post existingPost = PostResult.get();
        if (PostUpdate != null) {
            // Aggiorna i dettagli dell'utente solo se sono stati forniti nel payload
            if (PostUpdate.getTitle() != null) {
                existingPost.setTitle(PostUpdate.getTitle());
            }
            if (PostUpdate.getDescription() != null) {
                existingPost.setDescription(PostUpdate.getDescription());
            }
            if (PostUpdate.getImageUrl() != null) {
                existingPost.setImageUrl(PostUpdate.getImageUrl());
            }

            if (existingPost != null) {
                PostRepository.save(existingPost);
            } else {
                throw new ErrorHandler(400, "Post to save is null");
            }
            return existingPost;
        } else {
            throw new ErrorHandler(400, "Post update details are null");
        }
    }

    // D
    public void delete(Long id) {
        if (id != null) {
            PostRepository.deleteById(id);
        } else {
            throw new ErrorHandler(404, "Post not found");
        }
    }
}
