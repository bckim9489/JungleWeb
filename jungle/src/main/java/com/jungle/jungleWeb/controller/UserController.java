package com.jungle.jungleweb.controller;

import com.jungle.jungleweb.domain.ResponseDTO;
import com.jungle.jungleweb.domain.UserDTO;
import com.jungle.jungleweb.domain.entity.User;
import com.jungle.jungleweb.security.TokenProvider;
import com.jungle.jungleweb.service.UserService;
import com.jungle.jungleweb.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        try{
            if(userDTO == null || userDTO.getPassword() == null){
                throw new RuntimeException("Invalid Password value.");
            }

            User user = User.builder()
                    .userId(userDTO.getUserId())
                    .userPw(passwordEncoder.encode(userDTO.getPassword()))
                    .userNm(userDTO.getUserNm())
                    .build();

            User registeredUser = userService.create(user);

            UserDTO responseUserDTO = UserDTO.builder()
                    .uid(registeredUser.getUid())
                    .userNm(registeredUser.getUserNm())
                    .userId(registeredUser.getUserId())
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception e){
            ResponseDTO<String> responseDTO = ResponseUtil.ERROR(e.getMessage(),"");
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){
        User user = userService.getByCredentials(
                userDTO.getUserId(),
                userDTO.getPassword(),
                passwordEncoder
        );

        if(user != null){
            final String token = tokenProvider.create(user);

            final UserDTO responseUserDTO = UserDTO.builder()
                    .userId(user.getUserId())
                    .uid(user.getUid())
                    .userNm(user.getUserNm())
                    .token(token)
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);

        } else {
            ResponseDTO<String> responseDTO = ResponseUtil.FAILURE("Login Failed", "");
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


}
