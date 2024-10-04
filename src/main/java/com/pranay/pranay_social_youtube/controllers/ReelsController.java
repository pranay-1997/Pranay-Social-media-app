package com.pranay.pranay_social_youtube.controllers;

import com.pranay.pranay_social_youtube.model.Reels;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.services.ReelsService;
import com.pranay.pranay_social_youtube.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {
    @Autowired
    private ReelsService reelsService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader
            ("Authorization") String jwt){
        User reqUser= userService.finduserByJwt(jwt);

        Reels createdReels=reelsService.createReels(reel,reqUser);
        return createdReels;
    }

    @GetMapping ("/api/reels")
    public List<Reels> findAllReels(){
     //   User reqUser= userService.finduserByJwt(jwt);

       List<Reels> reels=reelsService.findAllReels();
        return reels;
    }

    @GetMapping ("/api/reels/user/{userId}")
    public List<Reels> findUsersReels(@PathVariable Integer userId) throws Exception {
        //   User reqUser= userService.finduserByJwt(jwt);

        List<Reels> reels=reelsService.findUsersReel(userId);
        return reels;
    }

}
