package com.instabasic.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instabasic.backend.model.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
