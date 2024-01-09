package com.jungle.jungleweb.service;

import com.jungle.jungleweb.domain.entity.User;
import com.jungle.jungleweb.exception.ResourceNotFoundException;
import com.jungle.jungleweb.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

    public User create(final User user){
        if(user == null || user.getUserId() == null){
            throw new RuntimeException("Invalid arguments");
        }
        final String userId = user.getUserId();

        if(userRepository.existsByUserId(userId)){
            log.warn("Username already exists {}", userId);
            throw new RuntimeException("Username already exists");
        }

        return userRepository.save(user);
    }

    public User getByCredentials(final String userId, final String password, final PasswordEncoder encoder){
        final User originalUser = userRepository.findByUserId(userId);
        if(originalUser != null && encoder.matches(password, originalUser.getUserPw())){
            return originalUser;
        }
        return null;
    }

}
