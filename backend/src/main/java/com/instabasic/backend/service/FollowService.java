package com.instabasic.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            // controllare che il follow non ci sia già
            Optional<Follow> followRecord = FollowRepository.findByFollowedAndFollower(follower, following);
            Boolean isPresent = followRecord.isPresent();
            if (isPresent) {
                Follow fRecord = followRecord.get();
                logger.info(fRecord.toString());
                // delete(follower.getId(), following.getId());
            } else {
                FollowRepository.save(follow);
            }
            return follow;
        } else {
            if (follower != null)
                throw new ErrorHandler(404, "Profilo non trovato");
            else
                throw new ErrorHandler(404, "Following non trovato");
        }
    }

    // R

    public List<Profile> getFollowing(String Profilename) {
        /* Restituisce i profili che quella persona(Profilename) segue */
        Profile profile = ProfileRepository.findByProfilename(Profilename).get();
        if (profile != null) {
            List<Follow> followingList = FollowRepository.findByFollowed(profile);
            List<Profile> followingProfiles = new ArrayList<>();
            for (Follow follow : followingList) {
                followingProfiles.add(follow.getFollower());
            }
            return followingProfiles;
        }
        return new ArrayList<>();
    }

    public List<Profile> getFollowers(String Profilename) {
        /* Restituisce i profili che seguono quella persona(Profilename) */
        Profile profile = ProfileRepository.findByProfilename(Profilename).get();
        if (profile != null) {
            List<Follow> followingList = FollowRepository.findByFollower(profile);
            List<Profile> followingProfiles = new ArrayList<>();
            for (Follow follow : followingList) {
                followingProfiles.add(follow.getFollowed());
            }
            return followingProfiles;
        }
        return new ArrayList<>();
    }

    // U

    // D
    public void delete(String profilename, String followingname) {
        try {
            if (profilename == null) {
                throw new ErrorHandler(400, "profileId is null");
            }
            if (followingname == null) {
                throw new ErrorHandler(400, "followingId is null");
            }
            Profile profile = ProfileRepository.findByProfilename(profilename).get();
            Profile following = ProfileRepository.findByProfilename(followingname).get();
            Optional<Follow> followRecord = FollowRepository.findByFollowedAndFollower(profile, following);
            Boolean isPresent = followRecord.isPresent();
            if (isPresent) {
                Follow followR = followRecord.get();
                Long id = followR.getId();
                if (id != null) {
                    FollowRepository.deleteById(id);
                } else {
                    throw new ErrorHandler(400, "Record Id is null");
                }
            } else {
                throw new ErrorHandler(404, "Follow not found");
            }
        } catch (Exception e) {
            logger.error("Unexpected error: " + e.getMessage(), e);
        }
    }
}
