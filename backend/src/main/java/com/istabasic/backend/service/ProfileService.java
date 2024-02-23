package com.istabasic.backend.service;

import com.istabasic.backend.controller.CommentController;
import com.istabasic.backend.model.Follow;
import com.istabasic.backend.model.Post;
import com.istabasic.backend.model.Profile;
import com.istabasic.backend.repository.FollowRepository;
import com.istabasic.backend.repository.PostRepository;
import com.istabasic.backend.repository.ProfileRepository;

import java.util.List;
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
    public Profile save(Profile Profile) {
        return ProfileRepository.save(Profile);
    }

    // R
    public Optional<Profile> findById(Long id) {
        return ProfileRepository.findById(id);
    }

    public Profile findByProfilename(String name) {
        Profile Profile = null;
        Optional<Profile> optional = ProfileRepository.findByProfilename(name);
        if (optional.isPresent()) {
            Profile = optional.get();
        } else {
            logger.warn("Profile not found");
        }
        return Profile;

    }

    public Page<Profile> findByUserId(String userId, Pageable pageable) {
        return ProfileRepository.findByUserId(userId, pageable);
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
        Optional<Profile> ProfileResult = ProfileRepository.findById(id);

        if (ProfileResult.isPresent()) {
            Profile ProfileUpdate = ProfileResult.get();

            ProfileRepository.save(ProfileUpdate);
            return ProfileUpdate;
        } else {
            throw new Error("Profile not found"); // TODO:GESTIONE ERRORI
        }

    }

    // D
    public void delete(Long id) {
        ProfileRepository.deleteById(id);
    }
}
