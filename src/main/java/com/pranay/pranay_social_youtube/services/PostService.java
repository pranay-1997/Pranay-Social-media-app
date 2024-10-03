package com.pranay.pranay_social_youtube.services;

import com.pranay.pranay_social_youtube.model.Post;

import java.util.List;

public interface PostService {

    Post createNewPost(Post post, Integer userId) throws Exception;
    public String deletePost(Integer postId,Integer userId) throws Exception;
    List<Post> findPostByUserId(Integer userId);
    Post findPostById(Integer postId) throws Exception;
    List<Post> finaAllPost();
    Post savedPost(Integer postId,Integer userId) throws Exception;
    Post likePost(Integer postId,Integer userId) throws Exception;

}
