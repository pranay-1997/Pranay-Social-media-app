package com.pranay.pranay_social_youtube.repository;

import com.pranay.pranay_social_youtube.model.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels,Integer> {
    public List<Reels> findByUserId(Integer userId);
}
