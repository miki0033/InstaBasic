package com.istabasic.backend.repository;

import com.istabasic.backend.model.Follow;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    // List<Follow> findAllbyProfile(Profile profile);

}
