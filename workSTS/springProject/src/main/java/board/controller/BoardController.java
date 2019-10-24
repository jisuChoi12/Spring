package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(value = "/board/boardWriteForm", method = RequestMethod.GET)
	public ModelAndView writeForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display", "/board/boardWriteForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

	@RequestMapping(value = "/board/boardWrite", method = RequestMethod.POST)
	@ResponseBody
	public void boardWrite(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
		boardDTO.setId(session.getAttribute("memId").toString());
		boardDTO.setName(session.getAttribute("memName").toString());
		boardDTO.setEmail(session.getAttribute("memEmail").toString());
		boardService.boardWrite(boardDTO);
	}

	@RequestMapping(value = "/board/boardList", method = RequestMethod.GET)
	public String boardList(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/board/boardList.jsp");
		return "/main/index";
	}

	@RequestMapping(value = "/board/getBoardList", method = RequestMethod.POST)
	public ModelAndView getBoardList(@RequestParam(required = false, defaultValue = "1") String pg, HttpSession session) {
		// 세션
		String memId = (String) session.getAttribute("memId");
		
		// 1페이지당 5개씩
		int endNum = Integer.parseInt(pg) * 5;
		int startNum = endNum - 4;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);

		List<BoardDTO> list = boardService.getBoardList(map);

		// 페이징 처리
		int totalA = boardService.getTotalA();
		int totalPg = (totalA + 4) / 5;
		boardPaging.setCurrentPage(Integer.parseInt(pg)); // 현재 페이지는 pg
		boardPaging.setPageBlock(3); // 1블록당 페이지 3개씩
		boardPaging.setPageSize(5); // 1페이지당 글 5개씩
		boardPaging.setTotalA(totalA); // 총글수
		boardPaging.makePagingHTML(); // 페이징html

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("boardPaging", boardPaging);
		mav.addObject("memId", memId);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="/board/boardView",method = RequestMethod.GET)
	public String boardView(@RequestParam String seq, String pg, Model model, HttpSession session) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display","/board/boardView.jsp");
		model.addAttribute("memId", (String) session.getAttribute("memId"));
		return "/main/index";
	}
	
	@RequestMapping(value="/board/getBoardView",method = RequestMethod.POST)
	public ModelAndView getBoardView(@RequestParam String seq) {
		ModelAndView mav = new ModelAndView();
		BoardDTO boardDTO = boardService.getBoardView(Integer.parseInt(seq));
		mav.addObject("boardDTO", boardDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="/board/boardSearch",method = RequestMethod.POST)
	public ModelAndView boardSearch(@RequestParam String searchOption, String keyword, String pg, HttpSession session) {
		// 1페이지당 5개씩
		int endNum = Integer.parseInt(pg) * 5;
		int startNum = endNum - 4;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption",searchOption);
		map.put("keyword", keyword);
		map.put("startNum", startNum);
		map.put("endNum", endNum);

		List<BoardDTO> list = boardService.getBoardSearchList(map);
		
		int totalA = boardService.getSearchTotalA(map);
		int totalPg = (totalA + 4) / 5;
		boardPaging.setCurrentPage(Integer.parseInt(pg)); // 현재 페이지는 pg
		boardPaging.setPageBlock(3); // 1블록당 페이지 3개씩
		boardPaging.setPageSize(5); // 1페이지당 글 5개씩
		boardPaging.setTotalA(totalA); // 총글수
		boardPaging.makePagingHTML(searchOption, keyword); // 페이징html

		ModelAndView mav = new ModelAndView();
		mav.addObject("searchOption",searchOption);
		mav.addObject("keyword",keyword);
		mav.addObject("list", list);
		mav.addObject("boardPaging", boardPaging);
		mav.addObject("memId", (String)session.getAttribute("memId"));
		mav.addObject("pg",pg);
		mav.setViewName("jsonView");
		return mav;
	}
	
}
