package com.pranay.pranay_social_youtube.controllers;

import com.pranay.pranay_social_youtube.model.Comment;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.services.UserService;
import com.pranay.pranay_social_youtube.services.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

@PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable("postId") Integer postId) throws Exception {
        User user=userService.finduserByJwt(jwt);
        Comment createdComment= commentService.createComment(comment,
                postId,
                user.getId());
        return createdComment;

    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer commentId) throws Exception {
        User user=userService.finduserByJwt(jwt);
        Comment likedComment= commentService.likeComment(commentId, user.getId());
        return likedComment;

    }

}
