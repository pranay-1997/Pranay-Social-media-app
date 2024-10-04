package com.pranay.pranay_social_youtube.controllers;

import com.pranay.pranay_social_youtube.exceptions.UserException;
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

    @GetMapping("/api/users")
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    @GetMapping("/api/users/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Integer id) throws UserException {
        return userService.findUserById(id);

    }



    @PutMapping("/api/users")
    public User UpdateUser( @RequestHeader("Authorization") String jwt,@RequestBody User user)
            throws UserException {
        User reqUser=userService.finduserByJwt(jwt);
        return userService.updateUser(user, reqUser.getId());

    }

    @PutMapping("/api/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer userId2)
            throws UserException{
        User reqUser=userService.finduserByJwt(jwt);
        User user=userService.followUser(reqUser.getId(),userId2);
        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> users=userService.searchUser(query);
        return users;
    }
    @GetMapping("/api/users/profile")
    public User getUserFromtoken(@RequestHeader("Authorization") String jwt){
        User user=userService.finduserByJwt(jwt);
        System.out.println("jwt-------"+jwt);
        user.setPassword(null);
        return user;
    }

}

