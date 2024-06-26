package com.instabasic.backend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;
import com.instabasic.backend.common.util.exception.ErrorHandler;
import com.instabasic.backend.common.util.security.jwt.JwtUtils;
import com.instabasic.backend.model.Post;
import com.instabasic.backend.service.PostService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PostController {
    static final Logger logger = LogManager.getLogger(PostController.class.getName());

    @Autowired
    private PostService PostService;
    @Autowired
    private JwtUtils JwtUtils;

    // C
    @PostMapping("/v1/newPost")
    public ResponseEntity<JsonNode> postPost(@RequestBody Post post) {
        try {
            PostService.save(post);
            return ResponseEntity.status(200).body(post.toJson());
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    // C
    @PostMapping("/v1/displayed/{profilename}/{postId}")
    public ResponseEntity<Boolean> displayed(@PathVariable String profilename, @PathVariable Long postId) {
        try {
            Boolean result = PostService.displayed(postId, profilename);
            return ResponseEntity.ok(result);// todo
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    // R
    @GetMapping("/v1/getPost/{postid}")
    public ResponseEntity<JsonNode> getPost(@PathVariable Long postid) {
        try {
            Post post = PostService.findById(postid);
            return ResponseEntity.status(200).body(post.toJson());
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    @GetMapping("/v1/getPosts/{profilename}")
    public Page<JsonNode> getPosts(@PathVariable String profilename, Pageable pageable) {
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
            return PostService.getPostsByFollow(profileId, pageable);
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    @GetMapping("/getStories/{profileId}")
    public Page<Post> getStory(@PathVariable String profileId, Pageable pageable) {
        // route per richiamare le story dei profili seguiti
        try {
            return PostService.getStories(profileId, pageable);
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
            return ResponseEntity.status(200).body(updatedPost.toString());
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

    // LIKE
    @PostMapping("/v1/likePost/{postId}")
    public ResponseEntity<String> addLike(@PathVariable Long postId,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = JwtUtils.getToken(authorizationHeader);
            String profilename = JwtUtils.getProfilenameFromJwtToken(token);
            boolean result = PostService.like(postId, profilename);
            if (result) {
                return ResponseEntity.status(200).body("Success");
            } else {
                return ResponseEntity.status(500).body("Failed");
            }
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    @GetMapping("/v1/isLiked/{postId}/{profilename}")
    public ResponseEntity<Boolean> isLiked(@PathVariable Long postId,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = JwtUtils.getToken(authorizationHeader);
            String profilename = JwtUtils.getProfilenameFromJwtToken(token);
            Boolean result = PostService.isLiked(postId, profilename);
            return ResponseEntity.ok(result);
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

}
