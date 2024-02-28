package com.instabasic.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.instabasic.backend.common.util.ErrorHandler;
import com.instabasic.backend.model.Follow;
import com.instabasic.backend.model.Profile;
import com.instabasic.backend.repository.FollowRepository;
import com.instabasic.backend.repository.PostRepository;

public class FollowService {
    @Autowired
    FollowRepository FollowRepository;
    @Autowired
    PostRepository PostRepository;

    // C
    public Follow save(Follow follow) {
        if (follow != null) {
            return FollowRepository.save(follow);
        } else {
            throw new ErrorHandler(400, "null");
        }
    }

    // R
    //TODO get() functions
    public List<Profile> getFollows() {
        return null;
    }

    public List<Profile> getFollower() {
        return null;
    }

}
