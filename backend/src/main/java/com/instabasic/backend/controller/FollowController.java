package com.instabasic.backend.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.instabasic.backend.service.FollowService;
import com.instabasic.backend.service.ProfileService;
import com.instabasic.backend.common.util.ErrorHandler;
import com.instabasic.backend.model.Follow;
import com.instabasic.backend.model.Profile;

@RestController
public class FollowController {
    static final Logger logger = LogManager.getLogger(FollowController.class.getName());

    @Autowired
    private FollowService followService;
    @Autowired
    private ProfileService profileService;

    // C
    @PostMapping("/v1/{followerUsername}/follow/{followingUsername}")
    public ResponseEntity<String> followUser(@PathVariable String followerUsername,
            @PathVariable String followingUsername) {
        try {
            Follow follow = followService.follow(followerUsername, followingUsername);
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

    /** Restituisce tutti i profili che quella persona segue */
    @GetMapping("/v1/getFollows/{Profileid}")
    public ResponseEntity<List<Follow>> getFollows(@PathVariable Long Profileid) {
        try {
            Profile profile = profileService.findById(Profileid);
            return ResponseEntity.status(200).body(followService.getFollowing(profile.getProfilename()));
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }

    }

    /** Restituisce tutti i profili che seguono quella persona */
    @GetMapping("/v1/getFollower/{Followerid}")
    public ResponseEntity<List<Follow>> getFollower(@PathVariable Long Profileid) {
        try {
            Profile profile = profileService.findById(Profileid);
            return ResponseEntity.status(200).body(followService.getFollowers(profile.getProfilename()));
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    // U

    // D
    @DeleteMapping("/v1/deleteFollow/{profileId}/{followerId}")
    public ResponseEntity<String> deleteFollow(@PathVariable Long profileId, @PathVariable Long followerId) {
        try {
            followService.delete(profileId, followerId);
            return ResponseEntity.status(200).body("Follow deleted");
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            return ResponseEntity.status(err.getStatus()).body(err.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

}
