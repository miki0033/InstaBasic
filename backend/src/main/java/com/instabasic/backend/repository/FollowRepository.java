package com.instabasic.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instabasic.backend.model.Follow;
import com.instabasic.backend.model.Profile;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findAllbyProfile(Profile profile);
    /* Tutti i profili che seguono quella persona */

    List<Follow> findAllbyFollower(Profile follower);
    /* Tutti i profili quella persona segue */

}
