package com.pranay.pranay_social_youtube.controllers;

import com.pranay.pranay_social_youtube.model.Message;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.services.MessageService;
import com.pranay.pranay_social_youtube.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {
        User user=userService.finduserByJwt(jwt);
        Message message=messageService.createMessage(user,chatId,req);
        return message;


    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatMessage(@RequestHeader("Authorization") String jwt,
                                         @PathVariable Integer chatId) throws Exception {
        User user=userService.finduserByJwt(jwt);
       List< Message> messages=messageService.findChatsMessages(chatId);
        return messages;


    }
}
