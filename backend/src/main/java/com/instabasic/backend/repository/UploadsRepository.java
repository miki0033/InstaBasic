package com.instabasic.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instabasic.backend.model.Uploads;

@Repository
public interface UploadsRepository extends JpaRepository<Uploads, Long> {

}
