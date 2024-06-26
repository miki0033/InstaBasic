package com.instabasic.backend.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.instabasic.backend.common.util.exception.ErrorHandler;
import com.instabasic.backend.model.Profile;
import com.instabasic.backend.model.User;
import com.instabasic.backend.model.project.ProfileProject;
import com.instabasic.backend.repository.FollowRepository;
import com.instabasic.backend.repository.PostRepository;
import com.instabasic.backend.repository.ProfileRepository;
import com.instabasic.backend.repository.UserRepository;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository ProfileRepository;

    @Autowired
    FollowRepository FollowRepository;

    @Autowired
    PostRepository PostRepository;

    @Autowired
    UserRepository userRepository;

    static final Logger logger = LogManager.getLogger(ProfileService.class.getName());

    // C
    public Profile save(Profile profile) {
        if (profile != null) {
            return ProfileRepository.save(profile);
        } else {
            throw new ErrorHandler(400, "null");
        }
    }

    public Profile save(ProfileProject project) {
        try {
            if (project != null) {
                Long userId = project.getUser();
                if (userId != null) {
                    User user = userRepository.findById(userId).get();
                    Profile profile = new Profile(project.getProfilename(), project.getFirstName(),
                            project.getLastName(),
                            project.getBirthday(), project.getBio(), user);
                    profile = ProfileRepository.save(profile);
                    return profile;
                } else {
                    throw new ErrorHandler(400, "userId null");
                }
            } else {
                throw new ErrorHandler(400, "null");
            }
        } catch (Exception e) {
            throw new ErrorHandler(500, e.getMessage());
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

    public Page<Profile> findByUserId(Long userId, Pageable pageable) {
        if (userId != null) {
            return ProfileRepository.findByUserId(userId, pageable);
        } else {
            throw new ErrorHandler(400, "userId=null");
        }
    }

    public Profile findFirstByUserId(Long userId) {
        if (userId != null) {
            return ProfileRepository.findFirstByUserId(userId).get();
        } else {
            throw new ErrorHandler(400, "userId=null");
        }
    }

    // U
    public Profile update(Long id, Profile profileUpdate) {
        if (id == null) {
            throw new ErrorHandler(400, "Profile id is null");
        }
        Optional<Profile> profileResult = ProfileRepository.findById(id);
        if (!profileResult.isPresent()) {
            throw new ErrorHandler(404, "Profile not found");
        }
        Profile existingProfile = profileResult.orElseThrow(() -> new ErrorHandler(400, "Profile not found"));
        if (profileUpdate != null) {
            // Aggiorna i dettagli dell'utente solo se sono stati forniti nel payload
            if (profileUpdate.getProfilename() != null
                    && profileUpdate.getProfilename() != existingProfile.getProfilename()) {
                existingProfile.setProfilename(profileUpdate.getProfilename());
            }
            if (profileUpdate.getFirstName() != null) {
                existingProfile.setFirstName(profileUpdate.getFirstName());
            }
            if (profileUpdate.getLastName() != null) {
                existingProfile.setLastName(profileUpdate.getLastName());
            }
            if (profileUpdate.getBirthday() != null) {
                existingProfile.setBirthday(profileUpdate.getBirthday());
            }
            if (profileUpdate.getBio() != null) {
                existingProfile.setBio(profileUpdate.getBio());
            }
            if (profileUpdate.getAvatarUrl() != null) {
                existingProfile.setAvatarUrl(profileUpdate.getAvatarUrl());
            }

            if (existingProfile != null) {
                ProfileRepository.save(existingProfile);
            } else {
                throw new ErrorHandler(400, "Profile to save is null");
            }
            return existingProfile;
        } else {
            throw new ErrorHandler(400, "Profile update details are null");
        }
    }

    // D
    public void delete(Long id) {
        if (id != null) {
            ProfileRepository.deleteById(id);
        } else {
            throw new ErrorHandler(404, "Profile not found");
        }
    }

    public void deleteForeignUser(Long id) {
        if (id != null) {
            List<Profile> listProfile = ProfileRepository.findByUserId(id);
            for (Profile prf : listProfile) {
                prf.setUser(null);
                ProfileRepository.save(prf);
            }
        } else {
            throw new ErrorHandler(404, "Profile not found");
        }
    }

    public Profile getDefaultProfile(Long userId) {
        Optional<Profile> opt = ProfileRepository.findFirstByUserId(userId);
        Profile profile = opt.get();
        return profile;
    }
}
