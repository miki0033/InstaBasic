package com.instabasic.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.instabasic.backend.common.util.exception.ErrorHandler;
import com.instabasic.backend.model.Post;
import com.instabasic.backend.model.Profile;

import com.instabasic.backend.repository.PostRepository;
import com.instabasic.backend.repository.ProfileRepository;

@Service
public class PostService {

    static final Logger logger = LogManager.getLogger(PostService.class.getName());

    @Autowired
    PostRepository postRepository;
    @Autowired
    ProfileRepository profileRepository;

    // C
    public Post save(Post post) {
        if (post != null) {

            post.setCreatedAt(LocalDateTime.now());

            return postRepository.save(post);
        } else {
            throw new ErrorHandler(400, "null");
        }
    }

    public boolean displayed(Long postId, String profilename) {
        try {
            if (postId != null && profilename != null) {
                Post post = postRepository.findById(postId).get();
                Profile profile = profileRepository.findByProfilename(profilename).get();
                post.addDisplayed(profile);
                postRepository.save(post);
                return post.isDisplayed(profile);
            } else
                throw new ErrorHandler(404, "postId or profileId at null");
        } catch (Exception e) {
            throw new ErrorHandler(500, e.getMessage());
        }

    }

    // R
    public Post findById(Long id) {
        if (id != null) {
            Optional<Post> optional = postRepository.findById(id);

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
        Optional<Post> optional = postRepository.findByTitleAndProfile(title, profile);
        if (optional.isPresent()) {
            Post = optional.get();
        } else {
            throw new ErrorHandler(400, "Post not found");
        }
        return Post;
    }

    public Page<JsonNode> getPostsByProfileName(String profileName, Pageable pageable) {
        try {
            if (profileName != null) {
                Page<Post> postPage = postRepository.findAllByProfile_Profilename(profileName,
                        pageable);
                List<Post> postlist = postPage.getContent();
                List<JsonNode> jsonPostList = new ArrayList<>();
                for (Post p : postlist) {
                    JsonNode jsonPost = p.toJson(); // Converti il post in JSON
                    jsonPostList.add(jsonPost);
                }
                Page<JsonNode> jsonPostPage = new PageImpl<>(jsonPostList, postPage.getPageable(),
                        postPage.getTotalElements());
                return jsonPostPage;
            } else {
                throw new ErrorHandler(400, "null");
            }
        } catch (Exception e) {
            throw new ErrorHandler(400, e.getMessage());
        }
    }

    public Page<Post> getPostsByFollow(String profileId, Pageable pageable) {
        /* Ritorna i post dei profili seguiti */
        try {
            Long id = Long.parseLong(profileId);
            Page<Post> followers = postRepository.findPostsFromFollowedProfilesOrderByCreatedAtDesc(id,
                    pageable);
            return followers;
        } catch (Exception e) {
            logger.error("Error PostService:getPostsByFollow" + e.getMessage(), e);
            return null;
        }
    }

    public Page<Post> getStories(String profileId, Pageable pageable) {
        /* Ritorna le stories dei profili seguiti */
        try {
            LocalDateTime startDate = LocalDateTime.now().minusDays(1);
            Long id = Long.parseLong(profileId);
            Page<Post> followers = postRepository.findStoriesFromFollowedProfilesOrderByCreatedAtDesc(id, startDate,
                    pageable);
            return followers;
        } catch (Exception e) {
            logger.error("Error PostService:getStories" + e.getMessage(), e);
            return null;
        }
    }

    // U
    public Post update(Long id, Post PostUpdate) {
        if (id == null) {
            throw new ErrorHandler(400, "Post id is null");
        }
        Optional<Post> PostResult = postRepository.findById(id);
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
                postRepository.save(existingPost);
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
            postRepository.deleteById(id);
        } else {
            throw new ErrorHandler(404, "Post not found");
        }
    }

    // Like
    public boolean like(Long postid, String profileName) {
        try {
            if (postid != null && profileName != null) {
                Optional<Post> optpost = postRepository.findById(postid);
                Post post = optpost.get();
                Profile plike = profileRepository.findByProfilename(profileName).get();
                if (post.isLiked(plike)) {
                    post.removeLike(plike);
                } else {
                    post.addLike(plike);
                }
                postRepository.save(post);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new ErrorHandler(500, e.getMessage());
        }
    }

    public boolean isLiked(Long postid, String profileName) {
        try {
            if (postid != null && profileName != null) {
                Optional<Post> optpost = postRepository.findById(postid);
                Post post = optpost.get();
                Profile plike = profileRepository.findByProfilename(profileName).get();
                return post.isLiked(plike);
            } else
                throw new ErrorHandler(500, "postId or profilename at null");
        } catch (Exception e) {
            throw new ErrorHandler(500, e.getMessage());
        }
    }
}
