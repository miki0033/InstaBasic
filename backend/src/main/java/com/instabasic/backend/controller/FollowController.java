package com.instabasic.backend.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.instabasic.backend.service.FollowService;
import com.instabasic.backend.service.ProfileService;
import com.instabasic.backend.common.util.exception.ErrorHandler;
import com.instabasic.backend.model.Follow;
import com.instabasic.backend.model.Profile;

@CrossOrigin(origins = "*", maxAge = 3600)
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
            return ResponseEntity.status(200).body(follow.toJSON());
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
    public ResponseEntity<List<Profile>> getFollows(@PathVariable Long Profileid) {
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
    @GetMapping("/v1/getFollowers/{Followerid}")
    public ResponseEntity<List<Profile>> getFollower(@PathVariable Long Followerid) {
        try {
            Profile profile = profileService.findById(Followerid);
            List<Profile> response = followService.getFollowers(profile.getProfilename());
            return ResponseEntity.status(200).body(response);
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
    @DeleteMapping("/v1/deleteFollow/{Profilename}/{Followingname}")
    public ResponseEntity<String> deleteFollow(@PathVariable String Profilename, @PathVariable String Followingname) {
        try {
            followService.delete(Profilename, Followingname);
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
