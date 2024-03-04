package com.instabasic.backend.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.instabasic.backend.common.util.ErrorHandler;
import com.instabasic.backend.model.User;
import com.instabasic.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository UserRepository;

    static final Logger logger = LogManager.getLogger(ProfileService.class.getName());

    // C
    public User save(User user) {
        if (user != null) {
            return UserRepository.save(user);
        } else {
            throw new ErrorHandler(400, "null");
        }
    }

    // R
    public User findById(Long id) {
        if (id != null) {
            Optional<User> optional = UserRepository.findById(id);
            if (optional.isPresent()) {
                return optional.get();
            } else {
                throw new ErrorHandler(404, "Comment not found");
            }
        } else {
            throw new ErrorHandler(400, "Id null");
        }
    }

    public User findByUsername(String name) {
        Optional<User> optional = UserRepository.findByUsername(name);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ErrorHandler(404, "User not found");
        }
    }

    public Page<User> findAll(Pageable pageable) {
        try {
            if (pageable != null) {
                return UserRepository.findAll(pageable);
            } else {
                throw new ErrorHandler(400, "pageable null");
            }
        } catch (Exception e) {
            logger.error("Error UserService:findAll" + e.getMessage(), e);
            return null;
        }
    }

    // U
    public User update(Long id, User userUpdate) {
        if (id == null) {
            throw new ErrorHandler(400, "User id is null");
        }
        Optional<User> userResult = UserRepository.findById(id);
        if (!userResult.isPresent()) {
            throw new ErrorHandler(404, "User not found");
        }
        User existingUser = userResult.get();
        if (userUpdate != null) {
            // Aggiorna i dettagli dell'utente solo se sono stati forniti nel payload
            if (userUpdate.getUsername() != null) {
                existingUser.setUsername(userUpdate.getUsername());
            }
            if (userUpdate.getEmail() != null) {
                existingUser.setEmail(userUpdate.getEmail());
            }
            if (userUpdate.getPassword() != null) {
                existingUser.setPassword(userUpdate.getPassword());
            }
            existingUser.setUpdatedAt(LocalDateTime.now());
            UserRepository.save(existingUser);
            return existingUser;
        } else {
            throw new ErrorHandler(400, "User update details are null");
        }

    }

    // D
    public void delete(Long id) {
        if (id != null) {
            UserRepository.deleteById(id);
        } else {
            throw new ErrorHandler(404, "User not found");
        }
    }
}
