package com.jungle.jungleweb.controller;

import com.jungle.jungleweb.domain.BoardUserDTO;
import com.jungle.jungleweb.domain.entity.Board;
import com.jungle.jungleweb.domain.entity.User;
import com.jungle.jungleweb.service.BoardService;
import com.jungle.jungleweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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


    /* BoardController */
    @PostMapping("/api/board")
    public Page<BoardUserDTO> getBoardList(@RequestBody Map<String, Object> commandMap){
        int page = Integer.parseInt(commandMap.getOrDefault("page", "0").toString());
        int size = Integer.parseInt(commandMap.get("size").toString());
        String search = commandMap.getOrDefault("search", "").toString();

        return boardService.findAll(page, size, search);
    }

    @GetMapping("/api/comment/{bid}")
    public List<BoardUserDTO> getCommentList(@PathVariable("bid") int bid){
        return boardService.findCommentList(bid);
    }

    @GetMapping("/api/board/{bid}")
    public BoardUserDTO getBoardDetail(@PathVariable("bid") int bid){
        return boardService.findOne(bid);
    }

    @PostMapping("/api/insert")
    public Board insertBoard(@RequestBody Map<String, Object> commandMap){
        String contents = commandMap.get("contents").toString();
        String title = commandMap.getOrDefault("title", "").toString();
        int uid = Integer.parseInt(commandMap.get("uid").toString());
        Object cidValue = commandMap.get("cid"); // "cid" 키에 해당하는 값을 가져옴
        Integer cid = null; // 기본값 설정

        if (cidValue != null) {
            try {
                cid = Integer.parseInt(cidValue.toString());
            } catch (NumberFormatException ignored) {
            }
        }

        User user = userService.findOne(uid);

        Board saveParams = Board.builder()
                .user(user)
                .title(title)
                .useYn('Y')
                .contents(contents)
                .cid(cid)
                .build();

        return boardService.insertOne(saveParams);
    }

    @PutMapping("/api/board")
    public Board updateBoard(@RequestBody Map<String, Object> commandMap){
        int bid = Integer.parseInt(commandMap.get("bid").toString());
        String contents = commandMap.get("contents").toString();
        String title = commandMap.get("title").toString();
        int uid = Integer.parseInt(commandMap.get("uid").toString());

        User user = userService.findOne(uid);

        Board saveParams = Board.builder()
                .bid(bid)
                .user(user)
                .title(title)
                .useYn('Y')
                .contents(contents)
                .build();

        return boardService.updateOne(saveParams);
    }

    @DeleteMapping("/api/board")
    public Board deleteBoard(@RequestBody Map<String, Object> commandMap){
        int bid = Integer.parseInt(commandMap.get("bid").toString());

        return boardService.deleteOne(bid);
    }

}
