package com.istabasic.backend.controller;

import com.istabasic.backend.model.Comment;
import com.istabasic.backend.service.CommentService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    static final Logger logger = LogManager.getLogger(CommentController.class.getName());
    @Autowired
    private CommentService CommentService;

    // C
    @PostMapping("/newComment")
    public String PostComment(@RequestBody Comment Comment) {
        CommentService.save(Comment);
        return Comment.toString();
    }

    // R
    @GetMapping("/getComment/{Commentid}")
    public Comment getComment(@PathVariable Long Commentid) {
        return CommentService.findById(Commentid);
    }

    /*
     * @GetMapping("/getComments/{profilename}")
     * public Page<Comment> getComments(@PathVariable String profilename, Pageable
     * pageable) {
     * return CommentService.getCommentsByProfileName(profilename, pageable);
     * }
     */

    @GetMapping("/getComments/{postid}")
    public Page<Comment> getCommentsByPost(@PathVariable String postid, Pageable pageable) {
        return CommentService.getCommentsByPost(postid, pageable);
    }

    @PutMapping("/updateComment/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody Comment CommentToUpdate) {
        Comment updatedComment = CommentService.update(id, CommentToUpdate);
        return "Comment updated with id: " + id + " " + updatedComment.toString();
    }

    @DeleteMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id) {
        CommentService.delete(id);
        return "Comment deleted with id: " + id;
    }
}
