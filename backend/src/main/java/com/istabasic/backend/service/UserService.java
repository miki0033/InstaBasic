package com.istabasic.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.istabasic.backend.model.User;
import com.istabasic.backend.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository UserRepository;

    // C
    public User save(User User) {
        return UserRepository.save(User);
    }

    // R
    public Optional<User> findById(Long id) {
        return UserRepository.findById(id);
    }

    public User findByUsername(String name) {
        User user = null;
        Optional<User> optional = UserRepository.findByUsername(name);
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new Error("User not found");
        }
        return user;

    }

    public Page<User> findAll(Pageable pageable) {
        return UserRepository.findAll(pageable);
    }

    // U
    public User update(Long id, User user) {
        Optional<User> userResult = UserRepository.findById(id);

        if (userResult.isPresent()) {
            User UserUpdate = userResult.get();

            UserRepository.save(UserUpdate);
            return UserUpdate;
        } else {
            throw new Error("User not found"); // TODO:GESTIONE ERRORI
        }

    }

    // D
    public void delete(Long id) {
        UserRepository.deleteById(id);
    }
}
