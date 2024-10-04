package com.pranay.pranay_social_youtube.services;

import com.pranay.pranay_social_youtube.model.Story;
import com.pranay.pranay_social_youtube.model.User;

import java.util.List;

public interface StoryService {

   // public Story CreateStory(Story story,Integer userId);

    Story CreateStory(Story story, User user);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
