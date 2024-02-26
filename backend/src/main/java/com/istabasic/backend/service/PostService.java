package com.istabasic.backend.service;

import com.istabasic.backend.common.util.ErrorHandler;
import com.istabasic.backend.model.Post;
import com.istabasic.backend.model.Profile;
import com.istabasic.backend.repository.PostRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Post update(Long id, Post Post) {
        try {
            if (id != null) {
                Optional<Post> PostResult = PostRepository.findById(id);
                if (PostResult.isPresent()) {
                    Post PostUpdate = PostResult.get();
                    if (PostUpdate != null) {
                        PostRepository.save(PostUpdate);
                    } else {
                        throw new ErrorHandler(404, "Post not found");
                    }
                    return PostUpdate;
                } else {
                    throw new ErrorHandler(404, "Post not found");
                }
            } else {
                throw new ErrorHandler(400, "id null");
            }
        } catch (Exception e) {
            throw new Error(e.getMessage());
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
