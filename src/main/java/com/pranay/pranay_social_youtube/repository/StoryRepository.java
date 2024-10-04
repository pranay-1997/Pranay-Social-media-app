package com.pranay.pranay_social_youtube.repository;

import com.pranay.pranay_social_youtube.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story,Integer> {

    public List<Story> findByUserId(Integer userId);
}
