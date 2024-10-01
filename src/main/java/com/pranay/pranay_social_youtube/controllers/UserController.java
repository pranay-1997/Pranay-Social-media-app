package com.pranay.pranay_social_youtube.controllers;

import com.pranay.pranay_social_youtube.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> users=new ArrayList<>();

        User user1=new User("pranay","K","pranaykdohni@gmail.com",
                "Pran568");
        User user2=new User("chinna","K","chinnakdohni@gmail.com",
                "Chin568");

        users.add(user1);
        users.add(user2);
        return users;
    }
}
