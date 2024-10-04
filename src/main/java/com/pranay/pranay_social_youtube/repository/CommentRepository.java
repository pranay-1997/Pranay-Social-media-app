package com.pranay.pranay_social_youtube.repository;

import com.pranay.pranay_social_youtube.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
