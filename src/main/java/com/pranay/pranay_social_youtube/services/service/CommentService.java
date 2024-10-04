package com.pranay.pranay_social_youtube.services.service;

import com.pranay.pranay_social_youtube.model.Comment;
import com.pranay.pranay_social_youtube.repository.CommentRepository;
import org.hibernate.annotations.Comments;

public interface CommentService {

    public Comment createComment(Comment comment,
                                 Integer postId, Integer userId) throws Exception;
    public Comment findCommentById(Integer commentId) throws Exception;
    public Comment likeComment(Integer commentId,Integer userId) throws Exception;


}
