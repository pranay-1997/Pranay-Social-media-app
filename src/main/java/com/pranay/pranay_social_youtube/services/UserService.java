package com.pranay.pranay_social_youtube.services;

import com.pranay.pranay_social_youtube.exceptions.UserException;
import com.pranay.pranay_social_youtube.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User registerUser(User user);
    public Optional<User> findUserById(Integer userId) throws UserException;
    public User findUserByEmail(String email);
    public User followUser(Integer userId1,Integer userId2) throws UserException;
    public User updateUser(User user,Integer userId) throws UserException;
    public List<User> searchUser(String query);
    public User finduserByJwt(String jwt);

}
