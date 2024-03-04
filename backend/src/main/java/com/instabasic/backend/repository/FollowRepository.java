package com.instabasic.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instabasic.backend.model.Follow;
import com.instabasic.backend.model.Profile;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findByFollowed(Profile followed);
    /* Tutti i profili che seguono quella persona */

    List<Follow> findByFollower(Profile follower);
    /* Tutti i profili quella persona segue */

}
