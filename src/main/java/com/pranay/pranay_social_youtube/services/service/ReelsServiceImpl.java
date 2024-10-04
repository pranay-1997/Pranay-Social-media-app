package com.pranay.pranay_social_youtube.services.service;

import com.pranay.pranay_social_youtube.model.Reels;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.repository.ReelsRepository;
import com.pranay.pranay_social_youtube.services.ReelsService;
import com.pranay.pranay_social_youtube.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReelsServiceImpl implements ReelsService {
    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private UserService userService;
    @Override
    public Reels createReels(Reels reel, User user) {
        Reels createReel=new Reels();
        createReel.setTitle(reel.getTitle());
        createReel.setUser(user);
        createReel.setVideo(reel.getVideo());

        return reelsRepository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {

        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Integer userId) throws Exception {
        userService.findUserById(userId).get();
        return reelsRepository.findByUserId(userId);
    }
}
