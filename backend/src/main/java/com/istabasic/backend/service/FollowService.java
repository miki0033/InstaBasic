package com.istabasic.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.istabasic.backend.common.util.ErrorHandler;
import com.istabasic.backend.model.Follow;
import com.istabasic.backend.model.Profile;
import com.istabasic.backend.repository.FollowRepository;
import com.istabasic.backend.repository.PostRepository;

public class FollowService {
    @Autowired
    FollowRepository FollowRepository;
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
    public List<Profile> getFollows() {
    }

    public List<Profile> getFollower() {
    }
}
