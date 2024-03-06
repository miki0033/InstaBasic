package com.instabasic.backend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instabasic.backend.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Page<Profile> findByUserId(Long userId, Pageable pageable);

    Optional<Profile> findFirstByUserId(Long userId);

    Optional<Profile> findByProfilename(String Profilename);

}