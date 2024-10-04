package com.pranay.pranay_social_youtube.controllers;

import com.pranay.pranay_social_youtube.model.Story;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.services.StoryService;
import com.pranay.pranay_social_youtube.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story,
                             @RequestHeader("Authorization") String jwt){
        User reqUser=userService.finduserByJwt(jwt);
        Story createdStory=storyService.CreateStory(story,reqUser);
        return createdStory;

    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUserStory(@PathVariable Integer userId,
                                     @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser=userService.finduserByJwt(jwt);
       List< Story> stories=storyService.findStoryByUserId(userId);
        return stories;

    }

}
