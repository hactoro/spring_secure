package com.example.springsecurity.Security.controller;

import com.example.springsecurity.Security.dto.BoardDto;
import com.example.springsecurity.Security.repository.BoardRepository;
import com.example.springsecurity.Security.repository.MemberRepository;
import com.example.springsecurity.Security.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/board/list")
    public ModelAndView userList(){

        ModelAndView mv = new ModelAndView();
        mv.addObject("list", boardService.getList());
        mv.setViewName("/list");

        return mv;
    }
    @GetMapping("/board/post")
    public String writeBoard(){
        return "post";
    }

    @PostMapping("/board/post")
    public String writeBoard(BoardDto boardDto){

        boardService.write(boardDto);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public ModelAndView modifyBoard(@PathVariable Long id){
        System.out.printf("modify id : " + id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("boardDto", boardService.findById(id));
        mv.setViewName("/modify");
        return mv;
    }

    @PostMapping("/board/modify")
    public String modifyBoard(BoardDto boardDto){
        System.out.println("modify post : " + boardDto.getId());
        System.out.println("modify post content" + boardDto.getContent());
        boardService.modify(boardDto);
        return "redirect:/board/list";
    }

    @PostMapping("/board/delete")
    public String deleteBoard(Long id){
        boardService.delete(id);
        return "redirect:/board/list";
    }

    @PostMapping("/board/like")
    public String likeBoard(Long id){
        boardService.addLike(id);
        return "redirect:/board/list";
    }
}
