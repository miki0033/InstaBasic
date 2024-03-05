package com.instabasic.backend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.instabasic.backend.common.util.exception.ErrorHandler;
import com.instabasic.backend.model.Post;
import com.instabasic.backend.service.PostService;
import com.instabasic.backend.service.ProfileService;

@RestController
public class PostController {
    static final Logger logger = LogManager.getLogger(PostController.class.getName());

    @Autowired
    private PostService PostService;

    @Autowired
    private ProfileService ProfileService;

    // C
    @PostMapping("/v1/newPost")
    public ResponseEntity<String> postPost(@RequestBody Post post) {
        try {
            PostService.save(post);
            return ResponseEntity.status(200).body(post.toString());
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            return ResponseEntity.status(err.getStatus()).body(err.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    // R
    @GetMapping("/v1/getPost/{postid}")
    public Post getPost(@PathVariable Long postid) {
        try {
            return PostService.findById(postid);
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    @GetMapping("/v1/getPosts/{profilename}")
    public Page<Post> getPosts(@PathVariable String profilename, Pageable pageable) {
        /* Ritorna tutti i post di un determinato profilo */
        try {
            return PostService.getPostsByProfileName(profilename, pageable);
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    @GetMapping("/v1/getPostsByFollow/{profileId}")
    public Page<Post> getPostsByFollow(@PathVariable String profileId, Pageable pageable) {
        /* Ritorna i post dei profili seguiti */
        try {
            return ProfileService.getPostsByFollow(profileId, pageable);
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    // U
    @PutMapping("/v1/updatePost/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody Post PostToUpdate) {
        try {
            Post updatedPost = PostService.update(id, PostToUpdate);
            return ResponseEntity.status(200).body("Post updated with id: " + id + " " + updatedPost.toString());
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            return ResponseEntity.status(err.getStatus()).body(err.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    // D
    @DeleteMapping("/v1/deletePost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        try {
            PostService.delete(id);
            return ResponseEntity.status(200).body("Post deleted with id: " + id);
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            return ResponseEntity.status(err.getStatus()).body(err.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

}
