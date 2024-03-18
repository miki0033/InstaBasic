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
import com.instabasic.backend.model.Comment;
import com.instabasic.backend.model.Post;
import com.instabasic.backend.model.project.CommentProject;
import com.instabasic.backend.repository.CommentRepository;
import com.instabasic.backend.repository.PostRepository;
import com.instabasic.backend.repository.ProfileRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentService {
    static final Logger logger = LogManager.getLogger(CommentService.class.getName());
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    CommentRepository CommentRepository;
    @Autowired
    PostRepository PostRepository;

    // C
    public Comment save(CommentProject project) {
        try {

            if (project != null) {
                Comment comment = new Comment(project);
                comment.setProfile(profileRepository.findById(project.getProfile())
                        .orElseThrow(() -> new EntityNotFoundException("Profile not found")));
                comment.setPost(PostRepository.findById(project.getPost())
                        .orElseThrow(() -> new EntityNotFoundException("Profile not found")));
                comment.setCreatedAt(LocalDateTime.now());
                // logger.info(comment);
                return CommentRepository.save(comment);
            } else {
                throw new ErrorHandler(400, "null");
            }
        } catch (EntityNotFoundException e) {
            throw new ErrorHandler(404, e.getMessage());
        }
    }

    // R
    public Comment findById(Long id) {
        if (id != null) {
            Optional<Comment> optional = CommentRepository.findById(id);

            if (optional.isPresent()) {
                return optional.get();
            } else {
                throw new ErrorHandler(404, "Comment not found");
            }
        } else {
            throw new ErrorHandler(400, "Id null");
        }
    }

    public Page<JsonNode> getCommentsByPost(String postid, Pageable pageable) {
        try {
            Long postId = Long.parseLong(postid);
            Optional<Post> optpost = PostRepository.findById(postId);
            Post post = optpost.get();
            Page<Comment> commPage = CommentRepository.findAllByPost(post, pageable);
            List<Comment> commlist = commPage.getContent();
            List<JsonNode> jsonCommList = new ArrayList<>();
            for (Comment c : commlist) {
                JsonNode jsonComm = c.toJSON(); // Converti il post in JSON
                jsonCommList.add(jsonComm);
            }
            Page<JsonNode> jsonPostPage = new PageImpl<>(jsonCommList, commPage.getPageable(),
                    commPage.getTotalElements());
            return jsonPostPage;
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            List<JsonNode> emptyList = new ArrayList<>();
            Page<JsonNode> emptyPage = new PageImpl<>(emptyList);
            return emptyPage;
        }

    }

    // U
    public Comment update(Long id, Comment CommentUpdate) {
        if (id == null) {
            throw new ErrorHandler(400, "Comment id is null");
        }
        Optional<Comment> CommentResult = CommentRepository.findById(id);
        if (!CommentResult.isPresent()) {
            throw new ErrorHandler(404, "Comment not found");
        }
        Comment existingComment = CommentResult.get();
        if (CommentUpdate != null) {
            // Aggiorna i dettagli dell'utente solo se sono stati forniti nel payload
            if (CommentUpdate.getText() != null) {
                existingComment.setText(CommentUpdate.getText());
            }
            if (existingComment != null) {
                CommentRepository.save(existingComment);
            } else {
                throw new ErrorHandler(400, "Comment to save is null");
            }
            return existingComment;
        } else {
            throw new ErrorHandler(400, "Comment update details are null");
        }
    }

    // D
    public void delete(Long id) {
        if (id != null) {
            CommentRepository.deleteById(id);
        } else {
            throw new ErrorHandler(404, "Comment not found");
        }
    }

}
