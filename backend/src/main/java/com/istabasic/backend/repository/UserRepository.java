package com.istabasic.backend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.istabasic.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByUsername(String username, Pageable pageable);

    Optional<User> findByUsername(String username);

}