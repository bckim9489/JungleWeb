package com.jungle.jungleweb.service;

import com.jungle.jungleweb.domain.BoardUserDTO;
import com.jungle.jungleweb.domain.entity.Board;
import com.jungle.jungleweb.exception.ResourceNotFoundException;
import com.jungle.jungleweb.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BoardService {

    final BoardRepository boardRepository;
    private BoardUserDTO convertToDto(Board board) {
        return BoardUserDTO.builder()
                .bid(board.getBid())
                .uid(board.getUser().getUid())
                .title(board.getTitle())
                .contents(board.getContents())
                .useYn(board.getUseYn())
                .userId(board.getUser().getUserId())
                .build();
    }

    public Page<BoardUserDTO> findAll(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Board> boards = boardRepository.findByUseYn('Y', pageRequest);
        return boards.map(this::convertToDto);
        /*
        return boards.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
         */
    }

    public BoardUserDTO findOne(int bid){
        Board board = boardRepository.findById(bid)
                .orElseThrow(()->new ResourceNotFoundException("Board", "bid", bid));
        return convertToDto(board);

    }

    public Board insertOne(Board board){
        return boardRepository.save(board);
    }

    public Board updateOne(Board board){
        return boardRepository.save(board);
    }
    public Board deleteOne(int bid, int uid){
        Board board = boardRepository.findById(bid)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        board.setUseYn('N');
        return boardRepository.save(board);
    }
}
