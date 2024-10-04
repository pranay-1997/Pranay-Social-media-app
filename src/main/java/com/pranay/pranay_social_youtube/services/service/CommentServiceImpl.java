package com.pranay.pranay_social_youtube.services.service;

import com.pranay.pranay_social_youtube.model.Comment;
import com.pranay.pranay_social_youtube.model.Post;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.repository.CommentRepository;
import com.pranay.pranay_social_youtube.repository.PostRepository;
import com.pranay.pranay_social_youtube.services.PostService;
import com.pranay.pranay_social_youtube.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
   private  PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public Comment createComment(Comment comment,
                                 Integer postId, Integer userId) throws Exception {
        User user=userService.findUserById(userId).get();
        Post post=postService.findPostById(postId);
        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        Comment savedComment=commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);
        return savedComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> opt=commentRepository.findById(commentId);
        if(opt.isEmpty()){
            throw new Exception("comment not exist");
        }

        return opt.get();
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        findCommentById(commentId);
        Comment comment=findCommentById(commentId);
        User user=userService.findUserById(userId).get();
        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }
        else{
            comment.getLiked().remove(user);
        }

        return commentRepository.save(comment);
    }
}
