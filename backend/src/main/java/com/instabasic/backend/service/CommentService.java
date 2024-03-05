package com.instabasic.backend.service;

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

import com.instabasic.backend.common.util.exception.ErrorHandler;
import com.instabasic.backend.model.Comment;
import com.instabasic.backend.model.Post;
import com.instabasic.backend.repository.CommentRepository;
import com.instabasic.backend.repository.PostRepository;

@Service
public class CommentService {
    static final Logger logger = LogManager.getLogger(CommentService.class.getName());
    @Autowired
    CommentRepository CommentRepository;

    PostRepository PostRepository;

    // C
    public Comment save(Comment Comment) {
        if (Comment != null) {
            return CommentRepository.save(Comment);
        } else {
            throw new ErrorHandler(400, "null");
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

    public Page<Comment> getCommentsByPost(String postid, Pageable pageable) {
        try {
            Long postId = Long.parseLong(postid);
            Optional<Post> optpost = PostRepository.findById(postId);
            Post post = optpost.get();
            return CommentRepository.findAllByPost(post, pageable);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            List<Comment> emptyList = new ArrayList<>();
            Page<Comment> emptyPage = new PageImpl<>(emptyList);
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
