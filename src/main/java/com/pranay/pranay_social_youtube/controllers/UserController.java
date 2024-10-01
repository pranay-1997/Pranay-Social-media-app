package com.pranay.pranay_social_youtube.controllers;

import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers(){

        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Integer id) throws Exception {

        Optional<User> user= userRepository.findById(id);
        if(user.isPresent()){
            return Optional.of(user.get());
        }
        throw new Exception("user not exist with userid"+id);
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setPassword(user.getPassword());
        newUser.setLastName(user.getLastName());
       // newUser.setId(user.getId());

        User savedUser=userRepository.save(newUser);
        return savedUser;
    }
    @PutMapping("/users/{userid}")
    public User UpdateUser( @PathVariable Integer userid,@RequestBody User user) throws Exception {
        Optional<User> user1=userRepository.findById(userid);
        if(user1.isEmpty()){
            throw new Exception("user is not found with the give id"+userid);
        }
        User oldUser=user1.get();

       //User oldUser= user1.get();
       if(user.getFirstName()!=null){
           oldUser.setFirstName(user.getFirstName());
       }
       if(user.getLastName()!=null){
           oldUser.setLastName(user.getLastName());
       }
       if(user.getEmail()!=null){
           oldUser.setEmail(user.getEmail());
       }
       if(user.getPassword()!=null){
           oldUser.setPassword(user.getPassword());
       }
       if(user.getId()!=null){
           oldUser.setId(user.getId());
       }
       User updatedUser= userRepository.save(oldUser);
       return updatedUser;

       }
     @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) throws Exception {
         Optional<User> user=userRepository.findById(userId);
         if(user.isEmpty()){
             throw new Exception("user is not found with the give id"+userId);
         }
          userRepository.delete(user.get());
        return "user deleted successfully"+userId;
    }
}
