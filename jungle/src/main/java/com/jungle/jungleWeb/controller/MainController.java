package com.jungle.jungleweb.controller;

import com.jungle.jungleweb.domain.entity.User;
import com.jungle.jungleweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/test")
    public String hello(){
        return "테스트입니다.";
    }

    @GetMapping("/api/all")
    public List<User> getUserList(){

        return userService.findAll();
    }

}
