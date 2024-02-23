package com.istabasic.backend.controller;

import com.istabasic.backend.model.Post;

import com.istabasic.backend.service.ProfileService;
import com.istabasic.backend.service.PostService;

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
@RequestMapping("/post")
public class PostController {
    static final Logger logger = LogManager.getLogger(PostController.class.getName());
    @Autowired
    private PostService PostService;
    private ProfileService ProfileService;

    // C
    @PostMapping("/v1/newPost")
    public String postPost(@RequestBody Post Post) {
        PostService.save(Post);
        return Post.toString();
    }

    // R
    @GetMapping("/v1/getPost/{postid}")
    public Post getPost(@PathVariable Long postid) {
        return PostService.findById(postid);
    }

    @GetMapping("/v1/getPosts/{profilename}")
    public Page<Post> getPosts(@PathVariable String profilename, Pageable pageable) {
        /* Ritorna tutti i post di un determinato profilo */
        return PostService.getPostsByProfileName(profilename, pageable);
    }

    @GetMapping("/v1/getPostsByFollow/{profileId}")
    public Page<Post> getPostsByFollow(@PathVariable String profileId, Pageable pageable) {
        /* Ritorna i post dei profili seguiti */
        return ProfileService.getPostsByFollow(profileId, pageable);
    }

    // U
    @PutMapping("/v1/updatePost/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody Post PostToUpdate) {
        Post updatedPost = PostService.update(id, PostToUpdate);
        return "Post updated with id: " + id + " " + updatedPost.toString();
    }

    // D
    @DeleteMapping("/v1/deletePost/{id}")
    public String deletePost(@PathVariable Long id) {
        PostService.delete(id);
        return "Post deleted with id: " + id;
    }

}
