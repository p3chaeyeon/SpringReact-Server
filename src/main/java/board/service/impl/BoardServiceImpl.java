package board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.bean.BoardDTO;
import board.dao.BoardDAO;
import board.service.BoardService;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public void boardWrite(String subject, String content) {
		String id = (String) session.getAttribute("memId");
		String name = (String) session.getAttribute("memName");
		String email = (String) session.getAttribute("memEmail");
		
		Map<String, String> map = new HashMap<>();
		map.put("subject", subject);
		map.put("content", content);
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		
		boardDAO.boardWrite(map);
		boardDAO.refUpdate();

	}

	@Override
	public List<BoardDTO> boardList() {
		List<BoardDTO> list = boardDAO.boardList();
		System.out.println(list);
		
		return list;
	}

}
