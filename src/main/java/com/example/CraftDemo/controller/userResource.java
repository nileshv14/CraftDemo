package com.example.CraftDemo.controller;

import com.example.CraftDemo.model.User;
import com.example.CraftDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/user")
public class userResource {

    @Autowired
    private UserService userService;

    @PutMapping(path = "/register")
    public String registerUser(@RequestBody User user) {

        return userService.saveUser(user);
    }

    @PatchMapping (path = "/verify")
    public String verifyUser(@RequestParam String email) {
        return userService.verifyUser(email);
    }

    @GetMapping(path = "/login")
    public String loginUser(@RequestParam String email,@RequestParam String password) {
        return userService.verifyUser(email);
    }
}
