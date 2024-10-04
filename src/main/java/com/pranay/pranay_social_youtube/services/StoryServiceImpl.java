package com.pranay.pranay_social_youtube.services;

import com.pranay.pranay_social_youtube.model.Story;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StoryServiceImpl implements StoryService{
    @Autowired
    StoryRepository storyRepository;
    @Autowired
    private UserService userService;


    @Override
    public Story CreateStory(Story story, User user) {
        Story createdStory=new Story();
        createdStory.setCaptions(story.getCaptions());
        createdStory.setImage(story.getImage());
        createdStory.setUser(user);
        createdStory.setTimestamp(story.getTimestamp());
        return storyRepository.save(createdStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        User user=userService.findUserById(userId).get();
       return storyRepository.findByUserId(userId);


    }
}
