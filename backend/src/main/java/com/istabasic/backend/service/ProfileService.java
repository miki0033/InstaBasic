package com.istabasic.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.istabasic.backend.model.Profile;

import com.istabasic.backend.repository.ProfileRepository;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository ProfileRepository;

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
            throw new Error("Profile not found");
        }
        return Profile;

    }

    public Page<Profile> findByUserId(String userId, Pageable pageable) {
        return ProfileRepository.findByUserId(userId, pageable);
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
