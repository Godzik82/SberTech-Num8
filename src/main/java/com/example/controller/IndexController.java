package com.example.controller;

import com.example.models.User;
import com.example.services.UsersService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UsersService usersService;


    @GetMapping("/")
    public String homePage (Model model) {
        return "base1";
    }

    @PostMapping("/registration")
    public String newUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        User user = new User(name, email, password);
        usersService.newUser(user);
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration() {
        log.info("GetMapping");
        return "registration";
    }

    @GetMapping("/login")
    public String loginUser() {
        return "login";
    }


    
}   