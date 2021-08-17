package com.example.springsecurity.Service;

import com.example.springsecurity.Security.repository.BoardRepository;
import com.example.springsecurity.Security.repository.LikeRepository;
import com.example.springsecurity.Security.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class boardService {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    LikeRepository likeRepository;

    @Test
    @Transactional
    public void getLikeCount(){
        boardService.getLike().forEach(
                like -> System.out.println("like count and " + like.getCount())
        );

        System.out.println("----------------------------------------------------");
        System.out.println(likeRepository.getById(1L).getBoardEntity().getTitle());

    }

}
