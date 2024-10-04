package com.pranay.pranay_social_youtube.controllers;

import com.pranay.pranay_social_youtube.model.Chat;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.request.CreateChatRequest;
import com.pranay.pranay_social_youtube.services.ChatService;
import com.pranay.pranay_social_youtube.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,
                           @RequestBody CreateChatRequest request) throws Exception {
        User reqUser=userService.finduserByJwt(jwt);
        User user2=userService.findUserById(request.getUserId()).get();

        Chat chat= chatService.createChat(reqUser,user2);
        return chat;
    }
    @GetMapping("/api/chats")
    public List<Chat> findUsersChat(
            @RequestHeader("Authorization") String jwt){
        User reqUser=userService.finduserByJwt(jwt);
       List< Chat> chats=chatService.findUsersChat(reqUser.getId());
       return chats;
    }
}
