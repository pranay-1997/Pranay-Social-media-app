package com.pranay.pranay_social_youtube.controllers;

import com.pranay.pranay_social_youtube.model.Post;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.response.ApiResponse;
import com.pranay.pranay_social_youtube.services.PostService;
import com.pranay.pranay_social_youtube.services.UserService;
import com.pranay.pranay_social_youtube.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost( @RequestHeader("Authorization") String jwt,
                                          @RequestBody Post post) throws Exception {
        User reqUser=userService.finduserByJwt(jwt);
        Post createdPost=postService.createNewPost(post,reqUser.getId());
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,
                                                  @RequestHeader("Authorization") String jwt)
            throws Exception {
        User reqUser=userService.finduserByJwt(jwt);
       String message = postService.deletePost(postId,reqUser.getId());
       ApiResponse response=new ApiResponse(message,true);
       return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
    }
    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post=postService.findPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);

    }
    @GetMapping("/api/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){
        List<Post> posts=postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }
    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> findAllPost(){
        List<Post> posts=postService.finaAllPost();
        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @PutMapping("/api/posts/save/{postId}")
   public ResponseEntity<Post> savedPost(@PathVariable Integer postId,
                                         @RequestHeader("Authorization") String jwt)
            throws Exception {
        User reqUser=userService.finduserByJwt(jwt);
        Post post=postService.savedPost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
   }

    @PutMapping("/api/posts/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,
                                                @RequestHeader("Authorization") String jwt)
            throws Exception {
        User reqUser=userService.finduserByJwt(jwt);
        Post post=postService.likePost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }


}
