package com.jungle.jungleweb.repository;

import com.jungle.jungleweb.domain.entity.Board;
import com.jungle.jungleweb.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Page<Board> findByUseYn(char y, Pageable pageable);
}
