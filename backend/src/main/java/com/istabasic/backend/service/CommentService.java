package com.istabasic.backend.service;

import com.istabasic.backend.model.Comment;
import com.istabasic.backend.model.Post;
import com.istabasic.backend.repository.CommentRepository;
import com.istabasic.backend.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository CommentRepository;
    PostRepository PostRepository;

    // C
    public Comment save(Comment Comment) {
        if (Comment != null) {
            return CommentRepository.save(Comment);
        }
        return null;
    }

    // R
    public Comment findById(Long id) {
        if (id != null) {
            Optional<Comment> optional = CommentRepository.findById(id);

            if (optional.isPresent()) {
                return optional.get();
            } else {
                throw new Error("Comment not found");
            }
        } else {
            throw new Error("Id null");
        }
    }

    public Page<Comment> getCommentsByPost(String postid, Pageable pageable) {
        try {
            Long postId = Long.parseLong(postid);
            Optional<Post> optpost = PostRepository.findById(postId);
            Post post = optpost.get();
            return CommentRepository.findAllByPost(post, pageable);
        } catch (Exception e) {
            // TODO: handle exception
            List<Comment> emptyList = new ArrayList<>();
            Page<Comment> emptyPage = new PageImpl<>(emptyList);
            return emptyPage;
        }

    }

    // U
    public Comment update(Long id, Comment Comment) {
        try {
            if (id != null) {
                Optional<Comment> CommentResult = CommentRepository.findById(id);
                if (CommentResult.isPresent()) {
                    Comment CommentUpdate = CommentResult.get();
                    CommentRepository.save(CommentUpdate);
                    return CommentUpdate;
                } else {
                    throw new Error("Comment not found"); // TODO:GESTIONE ERRORI
                }
            } else {
                throw new Error("id null");
            }
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }

    }

    // D
    public void delete(Long id) {
        if (id != null) {
            CommentRepository.deleteById(id);
        } else {
            throw new Error("Comment not found");
        }
    }

}
