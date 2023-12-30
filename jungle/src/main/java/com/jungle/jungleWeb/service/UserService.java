package com.jungle.jungleweb.service;

import com.jungle.jungleweb.domain.entity.User;
import com.jungle.jungleweb.exception.ResourceNotFoundException;
import com.jungle.jungleweb.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findOne(int uid){
        return userRepository.findById(uid)
                .orElseThrow(()->new ResourceNotFoundException("User", "uid", uid));
    }

}
