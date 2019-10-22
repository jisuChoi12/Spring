package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;
import member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService; // DB와 Controller연결

//	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
//	public ModelAndView login(@RequestParam String id, @RequestParam String pwd, HttpSession session) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("id", id);
//		map.put("pwd", pwd);
//		MemberDTO memberDTO = memberService.login(map);
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("memberDTO", memberDTO);
//		String loginResult = null;
//		if(memberDTO != null) {
//			session.setAttribute("memName", memberDTO.getName());
//			session.setAttribute("memId", memberDTO.getId());
//			session.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
//		} else {
//			loginResult = "fail";
//		}
//		mav.addObject("display","/template/body.jsp");
//		mav.addObject("loginResult", loginResult);
//		mav.setViewName("/main/index");
//		return mav;
//	}

	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String id, @RequestParam String pwd, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		MemberDTO memberDTO = memberService.login(map);
		String loginResult = null;
		if (memberDTO == null) {
			loginResult = "fail";
		} else {
			loginResult = "success";
			session.setAttribute("memName", memberDTO.getName());
			session.setAttribute("memId", memberDTO.getId());
			session.setAttribute("memEmail", memberDTO.getEmail1() + "@" + memberDTO.getEmail2());
		}
		return loginResult;
	}

	@RequestMapping(value = "/member/logout", method = RequestMethod.POST)
	@ResponseBody
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@RequestMapping(value = "/member/writeForm", method = RequestMethod.GET)
	public ModelAndView writeForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display", "/member/writeForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

	@RequestMapping(value = "/member/checkId", method = RequestMethod.POST)
	@ResponseBody
	public String checkId(@RequestParam String id) {
		MemberDTO memberDTO = memberService.isExistId(id);
		String isExist = null;
		if (memberDTO == null) { // 사용가능
			isExist = "not_exist";
		} else { // 사용 불가능
			isExist = "exist";
		}
		return isExist;
	}

	@RequestMapping(value = "/member/write", method = RequestMethod.POST)
	@ResponseBody
	public void write(@ModelAttribute MemberDTO memberDTO) {
		memberService.write(memberDTO);
	}
	
	@RequestMapping(value="/member/checkPostForm", method = RequestMethod.GET)
	public String checkPost() {
		return "/member/checkPost";
	}

	@RequestMapping(value="/member/checkPost", method = RequestMethod.POST)
	public ModelAndView checkPost(@ModelAttribute ZipcodeDTO zipcodeDTO) {
		ModelAndView mav = new ModelAndView();
		Map<String, String> map = new HashMap<String, String>();
		map.put("sido", zipcodeDTO.getSido());
		map.put("sigungu", zipcodeDTO.getSigungu()); 
		map.put("roadname", zipcodeDTO.getRoadname()); 
		List<ZipcodeDTO> list = memberService.getZipcodeList(map);
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}
}
