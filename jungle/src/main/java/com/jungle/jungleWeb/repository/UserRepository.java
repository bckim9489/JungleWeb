package com.jungle.jungleweb.repository;

import com.jungle.jungleweb.domain.entity.Board;
import com.jungle.jungleweb.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserIdAndUserPw(String userId, String userPw);

    User findByUserId(String userId);

    Boolean existsByUserId(String userId);
}
