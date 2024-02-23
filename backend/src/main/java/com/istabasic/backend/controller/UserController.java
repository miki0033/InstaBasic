package com.istabasic.backend.controller;

import com.istabasic.backend.model.User;
import com.istabasic.backend.service.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/user")
public class UserController {
    static final Logger logger = LogManager.getLogger(UserController.class.getName());
    @Autowired
    private UserService UserService;

    // C
    @PostMapping("/v1/newUser")
    public String postUser(@RequestBody User User) {
        UserService.save(User);
        return User.toString();
    }

    // R
    @GetMapping("/v1/getUser/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return UserService.findById(id);
    }

    @GetMapping("/v1/getUser/{userName}")
    public User getUserByUsername(@PathVariable String userName) {
        return UserService.findByUsername(userName);
    }

    // U

    @PutMapping("/v1/updateUser/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User UserToUpdate) {
        User updatedUser = UserService.update(id, UserToUpdate);
        return "User updated with id: " + id + " " + updatedUser.toString();
    }

    // D
    @DeleteMapping("/v1/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        UserService.delete(id);
        return "User deleted with id: " + id;
    }

}
