package com.pranay.pranay_social_youtube.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String homeControllerHandler(){
        return "this is home Controller";
    }

}
