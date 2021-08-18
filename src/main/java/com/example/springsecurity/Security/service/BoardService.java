package com.example.springsecurity.Security.service;

import com.example.springsecurity.Security.dto.BoardDto;
import com.example.springsecurity.Security.dto.MemberDto;
import com.example.springsecurity.Security.entity.BoardEntity;
import com.example.springsecurity.Security.entity.LikeEntity;
import com.example.springsecurity.Security.entity.MemberEntity;
import com.example.springsecurity.Security.exception.JpaNotFoundCustomException;
import com.example.springsecurity.Security.repository.BoardRepository;
import com.example.springsecurity.Security.repository.LikeRepository;
import com.example.springsecurity.Security.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    MemberRepository memberRepository;

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
                boardDto.setLikeCount(likeRepository.findById(boardEntities.get(i).getId()).orElseThrow(Exception::new).getCount());
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

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();


        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setWriter(boardDto.getWriter());
        boardEntity.setTitle(boardDto.getTitle());
        boardEntity.setLoginUser(username);
        boardEntity.setContent(boardDto.getContent());

        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setBoardEntity(boardEntity);
        likeEntity.setCount(0);

        boardEntity.setLikeEntity(likeEntity);

        boardRepository.save(boardEntity);

        likeRepository.save(likeEntity);
    }

    public void modify(BoardDto boardDto){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        BoardEntity boardEntity = boardRepository.findById(boardDto.getId()).get();

        if(boardEntity.getLoginUser().equals(userDetails.getUsername())) {

            boardEntity.setContent(boardDto.getContent());
            boardEntity.setWriter(boardDto.getWriter());
            boardEntity.setTitle(boardDto.getTitle());

            boardRepository.save(boardEntity);

        }else{

        }
    }

    public BoardDto findById(Long id) {
        BoardEntity boardEntity = new BoardEntity();
        try {
            boardEntity = boardRepository.findById(id).orElseThrow(IllegalAccessException::new); // null 처리 기법 확인 필요.
        }catch(Exception e) {
            System.out.println(e.toString());
        }
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

    public MemberDto getLoginUser(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        String email = userDetails.getUsername();

        MemberDto memberDto = new MemberDto();
        MemberEntity memberEntity = new MemberEntity();
        memberEntity = memberRepository.findByEmail(email).orElseThrow(
            JpaNotFoundCustomException::new
        );

        memberEntity.builder()
                .id(memberEntity.getId())
                .email(memberEntity.getEmail())
                .build();

        return memberDto;
    }

}
