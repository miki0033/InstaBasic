package com.instabasic.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instabasic.backend.common.util.ErrorHandler;
import com.instabasic.backend.model.Follow;
import com.instabasic.backend.model.Profile;

import com.instabasic.backend.repository.FollowRepository;
import com.instabasic.backend.repository.ProfileRepository;

@Service
public class FollowService {

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

}
