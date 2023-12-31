package com.jungle.jungleweb.service;

import com.jungle.jungleweb.domain.entity.Board;
import com.jungle.jungleweb.exception.ResourceNotFoundException;
import com.jungle.jungleweb.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BoardService {

    final BoardRepository boardRepository;

    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    public Board findOne(int bid){
        return boardRepository.findById(bid)
                .orElseThrow(()->new ResourceNotFoundException("Board", "bid", bid));
    }

}
