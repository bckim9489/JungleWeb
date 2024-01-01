package com.jungle.jungleweb.controller;

import com.jungle.jungleweb.domain.entity.Board;
import com.jungle.jungleweb.domain.entity.User;
import com.jungle.jungleweb.service.BoardService;
import com.jungle.jungleweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    private UserService userService;
    private BoardService boardService;

    @Autowired
    public MainController(UserService userService, BoardService boardService) {
        this.userService = userService;
        this.boardService = boardService;
    }

    @GetMapping("/api/test")
    public String hello(){
        return "테스트입니다.";
    }

    @GetMapping("/api/all")
    public List<User> getUserList(){
        return userService.findAll();
    }

    @GetMapping("/api/board")
    public List<Board> getBoardList(){
        return boardService.findAll();
    }

    @GetMapping("/api/board/{bid}")
    public Board getBoardDetail(@PathVariable("bid") int bid){
        return boardService.findOne(bid);
    }

}
