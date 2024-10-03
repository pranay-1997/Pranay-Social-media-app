package com.pranay.pranay_social_youtube.controllers;

import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.repository.UserRepository;
import com.pranay.pranay_social_youtube.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Integer id) throws Exception {
        return userService.findUserById(id);

    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PutMapping("/users/{userid}")
    public User UpdateUser(@PathVariable Integer userid, @RequestBody User user)
            throws Exception {
        return userService.updateUser(user, userid);

    }

    @PutMapping("/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1,@PathVariable Integer userId2)
            throws Exception {
        User user=userService.followUser(userId1,userId2);
        return user;
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> users=userService.searchUser(query);
        return users;
    }

}

