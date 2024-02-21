package com.istabasic.backend.repository;

import com.istabasic.backend.model.Profile;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Page<Profile> findByUserId(String userId, Pageable pageable);

    Optional<Profile> findByProfilename(String Profilename);

}