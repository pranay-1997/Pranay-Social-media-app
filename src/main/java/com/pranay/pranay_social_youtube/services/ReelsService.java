package com.pranay.pranay_social_youtube.services;

import com.pranay.pranay_social_youtube.model.Reels;
import com.pranay.pranay_social_youtube.model.User;

import java.util.List;

public interface ReelsService {
    public Reels createReels(Reels reel, User user);
    public List<Reels> findAllReels();
    public List<Reels> findUsersReel(Integer userId) throws Exception;
}
