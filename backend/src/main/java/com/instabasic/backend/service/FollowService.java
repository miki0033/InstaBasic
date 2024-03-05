package com.instabasic.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instabasic.backend.common.util.exception.ErrorHandler;
import com.instabasic.backend.model.Follow;
import com.instabasic.backend.model.Profile;

import com.instabasic.backend.repository.FollowRepository;
import com.instabasic.backend.repository.ProfileRepository;

@Service
public class FollowService {
    private static final Logger logger = LoggerFactory.getLogger(FollowService.class);

    @Autowired
    FollowRepository FollowRepository;

    @Autowired
    ProfileRepository ProfileRepository;

    // C
    public Follow follow(String followerProfilename, String followingProfilename) {
        Profile follower = ProfileRepository.findByProfilename(followerProfilename).get();
        Profile following = ProfileRepository.findByProfilename(followingProfilename).get();

        if (follower != null && following != null) {
            Follow follow = new Follow(follower, following);
            FollowRepository.save(follow);
            return follow;
        } else {
            if (follower != null)
                throw new ErrorHandler(404, "Profilo non trovato");
            else
                throw new ErrorHandler(404, "Following non trovato");
        }
    }

    // R

    public List<Follow> getFollowing(String Profilename) {
        /* Restituisce i profili che quella persona(Profilename) segue */
        Profile profile = ProfileRepository.findByProfilename(Profilename).get();
        if (profile != null) {
            List<Follow> followingProfiles = FollowRepository.findByFollowed(profile);
            return followingProfiles;
        }
        return new ArrayList<>();
    }

    public List<Follow> getFollowers(String Profilename) {
        /* Restituisce i profili che seguono quella persona(Profilename) */
        Profile profile = ProfileRepository.findByProfilename(Profilename).get();
        if (profile != null) {
            List<Follow> followingProfiles = FollowRepository.findByFollower(profile);
            return followingProfiles;
        }
        return new ArrayList<>();
    }

    // U

    // D
    public void delete(Long profileId, Long followingId) {
        try {
            if (profileId == null) {
                throw new ErrorHandler(400, "profileId is null");
            }
            if (followingId == null) {
                throw new ErrorHandler(400, "followingId is null");
            }
            Profile profile = ProfileRepository.findById(profileId).get();
            Profile following = ProfileRepository.findById(followingId).get();
            Follow followRecord = FollowRepository.findByFollowedAndFollower(profile, following).get();
            if (followRecord != null) {
                Long id = followRecord.getId();
                if (id != null) {
                    FollowRepository.deleteById(id);
                } else {
                    throw new ErrorHandler(400, "Record Id is null");
                }

            } else {
                throw new ErrorHandler(404, "Post not found");
            }
        } catch (Exception e) {
            logger.error("Unexpected error: " + e.getMessage(), e);
        }
    }
}
