package com.istabasic.backend.service;

import com.istabasic.backend.common.util.ErrorHandler;

import com.istabasic.backend.model.Post;
import com.istabasic.backend.model.Profile;
import com.istabasic.backend.repository.FollowRepository;
import com.istabasic.backend.repository.PostRepository;
import com.istabasic.backend.repository.ProfileRepository;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository ProfileRepository;
    @Autowired
    FollowRepository FollowRepository;
    @Autowired
    PostRepository PostRepository;

    static final Logger logger = LogManager.getLogger(ProfileService.class.getName());

    // C
    public Profile save(Profile profile) {
        if (profile != null) {
            return ProfileRepository.save(profile);
        } else {
            throw new ErrorHandler(400, "null");
        }
    }

    // R
    public Profile findById(Long id) {
        if (id != null) {
            Optional<Profile> optional = ProfileRepository.findById(id);
            if (optional.isPresent()) {
                return optional.get();
            } else {
                throw new ErrorHandler(404, "Comment not found");
            }
        } else {
            throw new ErrorHandler(400, "Id null");
        }
    }

    public Profile findByProfilename(String name) {
        Optional<Profile> optional = ProfileRepository.findByProfilename(name);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ErrorHandler(404, "Profile not found");
        }
    }

    public Page<Profile> findByUserId(String userId, Pageable pageable) {
        if (userId == null) {
            return ProfileRepository.findByUserId(userId, pageable);
        } else {
            throw new ErrorHandler(400, "userId=null");
        }

    }

    public Page<Post> getPostsByFollow(String profileId, Pageable pageable) {
        /* Ritorna i post dei profili seguiti */
        try {
            Long id = Long.parseLong(profileId);
            Page<Post> followers = PostRepository.findPostsFromFollowedProfilesOrderByCreatedAtDesc(id,
                    pageable);
            return followers;

        } catch (Exception e) {

            logger.error("Error ProfileService:getPostsByFollow" + e.getMessage(), e);
            return null;
        }

    }

    // U
    public Profile update(Long id, Profile Profile) {
        Optional<Profile> ProfileResult = null;
        if (id != null) {
            ProfileResult = ProfileRepository.findById(id);
        } else {
            throw new ErrorHandler(400, "Profile id is null");
        }
        if (ProfileResult.isPresent()) {
            Profile ProfileUpdate = ProfileResult.get();
            if (ProfileUpdate != null) {
                ProfileRepository.save(ProfileUpdate);
                return ProfileUpdate;
            } else {
                throw new ErrorHandler(404, "Profile not found");
            }
        } else {
            throw new ErrorHandler(404, "Profile not found");
        }

    }

    // D
    public void delete(Long id) {
        if (id != null) {
            ProfileRepository.deleteById(id);
        } else {
            throw new ErrorHandler(404, "Comment not found");
        }
    }
}
