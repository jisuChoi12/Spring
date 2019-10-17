package user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import user.bean.UserDTO;
import user.service.UserService;

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
		mav.setViewName("jsonView"); // viewResolver말고 jsonView를 거쳐서 가라(json표기법으로 변환)
		return mav;
	}
}
