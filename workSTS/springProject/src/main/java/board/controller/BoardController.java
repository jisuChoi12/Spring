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
import board.dao.BoardDAO;
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

	// java_ee에서 한 것처럼 여기서 바로 DB 갔다와도 됨
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
	public ModelAndView getBoardView(@RequestParam String seq, @RequestParam String pg, HttpSession session) {
		BoardDTO boardDTO = boardService.getBoardView(Integer.parseInt(seq));
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardDTO", boardDTO);
//		mav.addObject("memId",(String)session.getAttribute("memId"));
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="/board/boardSearch",method = RequestMethod.POST)
	public ModelAndView boardSearch(@RequestParam Map<String, String> map, HttpSession session) {
		// 1페이지당 5개씩
		int endNum = Integer.parseInt(map.get("pg")) * 5;
		int startNum = endNum - 4;

		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");

		List<BoardDTO> list = boardService.getBoardSearchList(map);
		
		// 페이징 처리
		int totalA = boardService.getSearchTotalA(map);
		boardPaging.setCurrentPage(Integer.parseInt(map.get("pg"))); // 현재 페이지는 pg
		boardPaging.setPageBlock(3); // 1블록당 페이지 3개씩
		boardPaging.setPageSize(5); // 1페이지당 글 5개씩
		boardPaging.setTotalA(totalA); // 총글수
		boardPaging.makeSearchPagingHTML(); // 페이징html

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("searchOption",map.get("searchOption"));
		mav.addObject("keyword",map.get("keyword"));
		mav.addObject("boardPaging", boardPaging);
		mav.addObject("memId", (String)session.getAttribute("memId"));
		mav.addObject("pg",Integer.parseInt(map.get("pg"))); 
		mav.setViewName("jsonView");
		return mav;
	}
	
//	@RequestMapping(value="/board/boardSearch",method = RequestMethod.POST)
//	public ModelAndView boardSearch(@RequestParam String searchOption, String keyword, String pg, HttpSession session) {
//		// 1페이지당 5개씩
//		int endNum = Integer.parseInt(pg) * 5;
//		int startNum = endNum - 4;
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("searchOption",searchOption);
//		map.put("keyword", keyword);
//		map.put("startNum", startNum);
//		map.put("endNum", endNum);
//
//		List<BoardDTO> list = boardService.getBoardSearchList(map);
//		
//		int totalA = boardService.getSearchTotalA(map);
//		int totalPg = (totalA + 4) / 5;
//		boardPaging.setCurrentPage(Integer.parseInt(pg)); // 현재 페이지는 pg
//		boardPaging.setPageBlock(3); // 1블록당 페이지 3개씩
//		boardPaging.setPageSize(5); // 1페이지당 글 5개씩
//		boardPaging.setTotalA(totalA); // 총글수
//		boardPaging.makePagingHTML(searchOption, keyword); // 페이징html
//
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("searchOption",searchOption);
//		mav.addObject("keyword",keyword);
//		mav.addObject("list", list);
//		mav.addObject("boardPaging", boardPaging);
//		mav.addObject("memId", (String)session.getAttribute("memId"));
//		mav.addObject("pg",pg);
//		mav.setViewName("jsonView");
//		return mav;
//	}
	
	@RequestMapping(value = "/board/boardModifyForm", method = RequestMethod.POST)
	public String boardModifyForm(@RequestParam String seq, @RequestParam String pg, HttpSession session, Model model) {
		BoardDTO boardDTO = boardService.getBoardView(Integer.parseInt(seq));
		model.addAttribute("pg", pg);
		model.addAttribute("seq", seq);
		model.addAttribute("boardDTO", boardDTO);
		model.addAttribute("display", "/board/boardModifyForm.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value = "/board/boardModify", method = RequestMethod.POST)
	@ResponseBody
	public void boardModify(@ModelAttribute BoardDTO boardDTO,@RequestParam String pg, HttpSession session) {
		System.out.println(boardDTO.getSubject());
		System.out.println(boardDTO.getContent());
		System.out.println(boardDTO.getSeq());
		System.out.println(pg);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq", boardDTO.getSeq());
		map.put("subject", boardDTO.getSubject());
		map.put("content", boardDTO.getContent());
		
		boardService.boardModify(map);
	}
	
	@RequestMapping(value="/board/boardReplyForm", method=RequestMethod.POST)
	public String boardReplyForm(@RequestParam String seq, @RequestParam String pg, Model model) {
		model.addAttribute("pseq", seq);//원글번호
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/board/boardReplyForm.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value="/board/boardReply", method = RequestMethod.POST)
	@ResponseBody
	public void boardReply(@RequestParam Map<String, String> map, HttpSession session) {
		String id = (String)session.getAttribute("memId");
		String name = (String)session.getAttribute("memName");
		String email = (String)session.getAttribute("memEmail");
		
		// map안에는 이미 pseq, subject, content
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		
		// 원글
		BoardDTO pDTO = boardService.getBoardView(Integer.parseInt(map.get("pseq")));
		map.put("ref", pDTO.getRef()+"");
		map.put("lev", pDTO.getLev()+"");
		map.put("step", pDTO.getStep()+"");
		
		boardService.boardReply(map);
		
	}
	
	@RequestMapping(value="/board/boardDelete", method = RequestMethod.POST)
	public String boardDelete(@RequestParam String seq, String pg, Model model) {
		boardService.boardDelete(Integer.parseInt(seq));
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/board/boardList.jsp");
		return "/main/index";
	}
}













