package com.instabasic.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.instabasic.backend.model.Profile;
import com.instabasic.backend.service.ProfileService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class ProfileController {
    static final Logger logger = LogManager.getLogger(ProfileController.class.getName());
    @Autowired
    private ProfileService ProfileService;

    // C
    @PostMapping("/v1/newProfile")
    public String postProfile(@RequestBody Profile Profile) {
        ProfileService.save(Profile);
        return Profile.toString();
    }

    // R
    @GetMapping("/v1/getProfiles/{userId}")
    public Page<Profile> getProfiles(@PathVariable String userId) {
        try {
            Long id = Long.parseLong(userId);
            return ProfileService.findByUserId(id, Pageable.unpaged());
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }

    }

    @GetMapping("/v1/getProfile/{ProfileName}")
    public Profile getProfile(@PathVariable String ProfileName) {
        return ProfileService.findByProfilename(ProfileName);
    }

    // U
    @PutMapping("/v1/updateProfile/{id}")
    public String updateProfile(@PathVariable Long id, @RequestBody Profile ProfileToUpdate) {
        Profile updatedProfile = ProfileService.update(id, ProfileToUpdate);
        return "Profile updated with id: " + id + " " + updatedProfile.toString();
    }

    // D
    @DeleteMapping("/v1/deleteProfile/{id}")
    public String deleteProfile(@PathVariable Long id) {
        ProfileService.delete(id);
        return "Profile deleted with id: " + id;
    }

}
