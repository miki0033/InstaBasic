package com.istabasic.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.istabasic.backend.model.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    // List<Follow> findAllbyProfile(Profile profile);

}
