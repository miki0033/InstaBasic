package com.instabasic.backend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.istabasic.backend.common.util.ErrorHandler;
import com.istabasic.backend.model.Follow;
import com.istabasic.backend.service.CommentService;
import com.istabasic.backend.service.FollowService;

@RestController
public class FollowController {
    static final Logger logger = LogManager.getLogger(FollowController.class.getName());
    @Autowired
    private FollowService FollowService;

    // C
    @PostMapping("/v1/newFollow")
    public ResponseEntity<String> PostFollow(@RequestBody Follow follow) {
        try {
            FollowService.save(follow);
            return ResponseEntity.status(200).body(follow.toString());
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            return ResponseEntity.status(err.getStatus()).body(err.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    // R
    @GetMapping("/v1/getFollows/{Profileid}")
    public ResponseEntity<String> getFollows(@PathVariable Long Profileid) {
        /* Restituisce tutti i profili che quella persona segue */
        try {
            // TODO
            return ResponseEntity.status(200).body();
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            return ResponseEntity.status(err.getStatus()).body(err.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }

    }

    @GetMapping("/v1/getFollower/{Followerid}")
    public ResponseEntity<String> getFollower(@PathVariable Long Profileid) {
        /* Restituisce tutti i profili che seguono quella persona */
        // TODO
        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
