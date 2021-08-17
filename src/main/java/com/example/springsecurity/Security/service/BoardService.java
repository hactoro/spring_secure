package com.example.springsecurity.Security.service;

import com.example.springsecurity.Security.dto.BoardDto;
import com.example.springsecurity.Security.entity.BoardEntity;
import com.example.springsecurity.Security.entity.LikeEntity;
import com.example.springsecurity.Security.repository.BoardRepository;
import com.example.springsecurity.Security.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    LikeRepository likeRepository;

    public List<BoardDto> getList(){
        List<BoardDto> boardDtos = new ArrayList<>();
//        boardRepository.findAll().forEach(
//                board -> boardDtos.add(
//                        new BoardDto().setId(board.getId())
//                )
//        );
        List<BoardEntity> boardEntities = new ArrayList<>();
        boardEntities = boardRepository.findAll();
        for(int i = 0 ; i < boardEntities.size(); i++){

            BoardDto boardDto = new BoardDto();
            boardDto.setId(boardEntities.get(i).getId());
            boardDto.setWriter(boardEntities.get(i).getWriter());
            boardDto.setContent(boardEntities.get(i).getContent());
            boardDto.setTitle(boardEntities.get(i).getTitle());

            try{
                boardDto.setLikeCount(likeRepository.findById(boardEntities.get(i).getId()).orElseThrow().getCount());
            }catch(Exception e){
                System.out.println("something wrong in get like-count");
            }
//            boardDto.setLikeCount(likeRepository.findById(boardEntities.get(i).getId()).orElseThrow());

            boardDtos.add(boardDto);
        }
        return boardDtos;
    }

    public List<LikeEntity> getLike(){
        return likeRepository.findAll();
    }

    @Transactional
    public void write(BoardDto boardDto){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setWriter(boardDto.getWriter());
        boardEntity.setTitle(boardDto.getTitle());
        boardEntity.setContent(boardDto.getContent());

        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setBoardEntity(boardEntity);
        likeEntity.setCount(0);

        boardEntity.setLikeEntity(likeEntity);

        boardRepository.save(boardEntity);

        likeRepository.save(likeEntity);
    }

    public void modify(BoardDto boardDto){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity = boardRepository.findById(boardDto.getId()).orElseThrow();
        boardEntity.setContent(boardDto.getContent());
        boardEntity.setWriter(boardDto.getWriter());
        boardEntity.setTitle(boardDto.getTitle());

        boardRepository.save(boardEntity);

    }

    public BoardDto findById(Long id) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity = boardRepository.findById(id).orElseThrow(); // null 처리 기법 확인 필요.

        BoardDto boardDto = new BoardDto();
        boardDto.setId(boardEntity.getId());
        boardDto.setTitle(boardEntity.getTitle());
        boardDto.setWriter(boardEntity.getWriter());
        boardDto.setContent(boardEntity.getContent());

        return boardDto;
    }

    @Transactional
    public void delete(Long id){
        likeRepository.deleteById(boardRepository.getById(id).getLikeEntity().getId());
        boardRepository.deleteById(id);

    }

    public void addLike(Long id){
        LikeEntity likeEntity = boardRepository.getById(id).getLikeEntity();
        likeEntity.setCount(likeEntity.getCount() + 1);
        likeRepository.save(likeEntity);
    }

}
