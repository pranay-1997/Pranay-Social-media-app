package com.pranay.pranay_social_youtube.services;

import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setPassword(user.getPassword());
        newUser.setLastName(user.getLastName());
        // newUser.setId(user.getId());

        User savedUser=userRepository.save(newUser);
        return savedUser;

    }

    @Override
    public Optional<User> findUserById(Integer userId) throws Exception {
        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent()){
            return Optional.of(user.get());
        }
        throw new Exception("user not exist with userid"+userId);


    }

    @Override
    public User findUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {
        User user1= findUserById(userId1).get();
        User user2=findUserById(userId2).get();
        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());
        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }

    @Override
    public User updateUser(User user,Integer userId) throws Exception {
        Optional<User> user1=userRepository.findById(userId);
        if(user1.isEmpty()){
            throw new Exception("user is not found with the give id"+userId);
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


       // return null;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }
}
