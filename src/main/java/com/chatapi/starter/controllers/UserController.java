package com.chatapi.starter.controllers;

import com.chatapi.starter.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.chatapi.starter.repositories.user.UserRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        LOGGER.log(Level.FINE, "Request from to create new User", user.toString());
        return userRepository.createUser(user);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<List<User>> getUsers() {
        LOGGER.log(Level.INFO, "Request to get all users");
        List<User> users = userRepository.findAll();
        if (users == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        LOGGER.log(Level.INFO, "Request to fetch user with ID", id);
        User user = userRepository.findUser(id);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        LOGGER.log(Level.INFO, "user details {0}", user.toString());
        return ResponseEntity.ok(user);
    }

}
