package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardPaging boardPaging;
	
	@RequestMapping(value="/board/boardWriteForm", method = RequestMethod.GET)
	public ModelAndView writeForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display", "/board/boardWriteForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	
	@RequestMapping(value="/board/boardWrite",method = RequestMethod.POST)
	@ResponseBody
	public void boardWrite(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
		boardDTO.setId(session.getAttribute("memId").toString());
		boardDTO.setName(session.getAttribute("memName").toString());
		boardDTO.setEmail(session.getAttribute("memEmail").toString());
		boardService.boardWrite(boardDTO);
	}
	
	@RequestMapping(value="/board/boardListForm",method = RequestMethod.GET)
	public ModelAndView boardList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display","/board/boardList.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
}
