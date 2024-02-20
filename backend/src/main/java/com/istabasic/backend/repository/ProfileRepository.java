package com.istabasic.backend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.istabasic.backend.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Page<Profile> findByUserId(String userId, Pageable pageable);

    Optional<Profile> findByProfilename(String Profilename);

}