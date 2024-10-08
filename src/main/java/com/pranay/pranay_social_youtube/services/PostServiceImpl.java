package com.pranay.pranay_social_youtube.services;

import com.pranay.pranay_social_youtube.model.Post;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.repository.PostRepository;
import com.pranay.pranay_social_youtube.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements  PostService{

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        User user=userService.findUserById(userId).get();
        Post newPost=new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
       newPost.setCreatedAt(LocalTime.from(LocalDateTime.now()));
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
       // newPost.
        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post=findPostById(postId);
        User user=userService.findUserById(userId).get();
        if(post.getUser().getId()!=user.getId())
            throw new Exception("you can't delete another users post");

        postRepository.delete(post);
      return "post deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> opt=postRepository.findById(postId);
        if(opt.isEmpty()){
            throw new Exception("post not found with id"+postId);
        }
        return opt.get();
    }

    @Override
    public List<Post> finaAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post=findPostById(postId);
        User user=userService.findUserById(userId).get();
        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }
        else user.getSavedPost().add(post);
        userRepository.save(user);

        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post=findPostById(postId);
        User user=userService.findUserById(userId).get();
        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else{
            post.getLiked().add(user);

        }


        return postRepository.save(post);
    }
}
