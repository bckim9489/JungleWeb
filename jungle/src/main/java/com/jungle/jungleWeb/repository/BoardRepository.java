package com.jungle.jungleweb.repository;

import com.jungle.jungleweb.domain.entity.Board;
import com.jungle.jungleweb.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
}
