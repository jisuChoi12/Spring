package user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import user.bean.UserDTO;
import user.service.UserService;

// 요청이 오면 받아주기
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/writeForm", method = RequestMethod.GET)
	public String writeForm() {
		return "/user/writeForm";
	}

	@RequestMapping(value = "/user/write", method = RequestMethod.POST)
	@ResponseBody // 모든 컨트롤러는 viewResolver에 의해서 파일명.jsp로 가려고 한다. @ResponseBody를 써서 제자리(aJax)로 돌아가게 해야함. 이거 없으면 에러
	public void write(@ModelAttribute UserDTO userDTO) {
		userService.write(userDTO);
	}
	
	@RequestMapping(value="/user/list", method = RequestMethod.GET)
	public String list() {
		return "/user/list";
	}

	@RequestMapping(value="/user/getList", method = RequestMethod.POST)
	public ModelAndView getList() {
		List<UserDTO> list = userService.getList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("jsonView"); // viewResolver말고 jsonView를 거쳐서 가라(json표기법으로 변환) setViewName("xml에 지정된 id")	
		return mav;
	}
	
	@RequestMapping(value="/user/modifyForm", method = RequestMethod.GET)
	public String modifyForm() {
		return "/user/modifyForm";
	}
	
	@RequestMapping(value="/user/getUser", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam String id) {
		UserDTO userDTO = userService.getUser(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("userDTO", userDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="/user/modify", method = RequestMethod.POST)
	@ResponseBody // 모든 컨트롤러는 viewResolver에 의해서 파일명.jsp로 가려고 한다. @ResponseBody를 써서 제자리(aJax)로 돌아가게 해야함. 이거 없으면 에러
	public void modify(@ModelAttribute UserDTO userDTO) {
		userService.update(userDTO);
	}
	
	@RequestMapping(value="/user/deleteForm", method = RequestMethod.GET)
	public String deleteForm() {
		return "/user/deleteForm";
	}
	
	@RequestMapping(value="/user/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(@RequestParam String id) {
		userService.userDelete(id);
	}
	
	@RequestMapping(value="/user/isExistId", method = RequestMethod.POST)
	public ModelAndView isExistId(@RequestParam String id) {
		UserDTO userDTO = userService.isExistId(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("userDTO", userDTO);
		mav.setViewName("jsonView");
		return mav;
	}
}

