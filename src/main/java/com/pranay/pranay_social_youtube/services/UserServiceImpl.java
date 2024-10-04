package com.pranay.pranay_social_youtube.services;

import com.pranay.pranay_social_youtube.config.JwtProvider;
import com.pranay.pranay_social_youtube.exceptions.UserException;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
   private UserRepository userRepository;

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
    public Optional<User> findUserById(Integer userId) throws UserException {
        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent()){
            return Optional.of(user.get());
        }
        throw new UserException("user not exist with userid"+userId);


    }

    @Override
    public User findUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserException {
        User reqUser= findUserById(reqUserId).get();
        User user2=findUserById(userId2).get();
        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());
        userRepository.save(reqUser);
        userRepository.save(user2);
        return reqUser;
    }

    @Override
    public User updateUser(User user,Integer userId) throws UserException {
        Optional<User> user1=userRepository.findById(userId);
        if(user1.isEmpty()){
            throw new UserException("user is not found with the give id"+userId);
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
        if(user.getGender()!=null){
            oldUser.setGender(user.getGender());
        }
        User updatedUser= userRepository.save(oldUser);
        return updatedUser;


       // return null;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User finduserByJwt(String jwt) {
        String email= JwtProvider.getEmailFromJwtToken(jwt);
        User user=userRepository.findByEmail(email);
        return user;
    }
}
