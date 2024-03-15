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

import com.fasterxml.jackson.databind.JsonNode;
import com.instabasic.backend.common.util.exception.ErrorHandler;
import com.instabasic.backend.model.Comment;
import com.instabasic.backend.model.project.CommentProject;
import com.instabasic.backend.service.CommentService;

@RestController
public class CommentController {
    static final Logger logger = LogManager.getLogger(CommentController.class.getName());
    @Autowired
    private CommentService CommentService;

    // C
    @PostMapping("/v1/newComment")
    public ResponseEntity<JsonNode> PostComment(@RequestBody CommentProject Comment) {
        try {
            Comment responseComment = CommentService.save(Comment);
            return ResponseEntity.status(200).body(responseComment.toJSON());
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    // R
    @GetMapping("/v1/getComment/{Commentid}")
    public ResponseEntity<String> getComment(@PathVariable Long Commentid) {
        try {
            return ResponseEntity.status(200).body(CommentService.findById(Commentid).toString());
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            return ResponseEntity.status(err.getStatus()).body(err.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }

    }

    /*
     * @GetMapping("/v1/getComments/{profilename}")
     * public Page<Comment> getComments(@PathVariable String profilename, Pageable
     * pageable) {
     * return CommentService.getCommentsByProfileName(profilename, pageable);
     * }
     */

    @GetMapping("/v1/getComments/{postid}")
    public Page<Comment> getCommentsByPost(@PathVariable String postid, Pageable pageable) {
        try {
            return CommentService.getCommentsByPost(postid, pageable);
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }

    }

    // U
    @PutMapping("/v1/updateComment/{id}")
    public ResponseEntity<String> updateComment(@PathVariable Long id, @RequestBody Comment CommentToUpdate) {
        try {
            Comment updatedComment = CommentService.update(id, CommentToUpdate);
            return ResponseEntity.status(200).body("Comment updated with id: " + id + " " + updatedComment.toString());
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            return ResponseEntity.status(err.getStatus()).body(err.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }

    }

    @DeleteMapping("/v1/deleteComment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        try {
            CommentService.delete(id);
            return ResponseEntity.status(200).body("Comment deleted with id: " + id);
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            return ResponseEntity.status(err.getStatus()).body(err.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}
