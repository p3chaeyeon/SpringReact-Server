package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import board.bean.BoardDTO;
import board.service.BoardService;


/* SpringReact/src/main/member/controller/BoardController.java */

//@Controller
@RestController // @ResponseBody 없어도 바로 브라우저로 뿌려줌
@RequestMapping(value="/board")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
//	@RequestMapping(value="/boardWrite", method = RequestMethod.POST)
	@PostMapping(value="/boardWrite")
//	@ResponseBody
	public void boardWrite(@RequestParam String subject, @RequestParam String content) {
		System.out.println(subject);
		boardService.boardWrite(subject, content);
	}
	
	
	@GetMapping(value="/boardList")
//	@ResponseBody
	public List<BoardDTO> boardList() { // List<BoardDTO> 객체를 자동으로 JSON 배열로 변환하여 보냄
		return boardService.boardList();
		
	}
	
}
