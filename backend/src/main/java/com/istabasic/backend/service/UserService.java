package com.istabasic.backend.service;

import com.istabasic.backend.common.util.ErrorHandler;
import com.istabasic.backend.model.User;
import com.istabasic.backend.repository.UserRepository;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public User update(Long id, User user) {
        Optional<User> userResult = null;
        if (id != null) {
            userResult = UserRepository.findById(id);
        } else {
            throw new ErrorHandler(400, "User id is null");
        }
        if (userResult.isPresent()) {
            User UserUpdate = userResult.get();
            if (UserUpdate != null) {
                UserRepository.save(UserUpdate);
                return UserUpdate;
            } else {
                throw new ErrorHandler(404, "User not found");
            }

        } else {
            throw new ErrorHandler(404, "User not found");
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
