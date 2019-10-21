package user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
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
	@ResponseBody // 응답 요청 객체가 없음
	public Map<String, Object> getList() {
		List<UserDTO> list = userService.getList();
		JSONArray jsonArray = JSONArray.fromObject(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", jsonArray);
		return map;
	}
	
//	@RequestMapping(value="/user/getList", method = RequestMethod.POST)
//	public ModelAndView getList() {
//		List<UserDTO> list = userService.getList();
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("list", list);
//		mav.setViewName("jsonView"); // viewResolver말고 jsonView를 거쳐서 가라(json표기법으로 변환) setViewName("xml에 지정된 id")	
//		return mav;
//	}
	
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
	
//	text로 받을 때
	@RequestMapping(value="/user/isExistId", method = RequestMethod.POST)
	@ResponseBody
	public String isExistId(@RequestParam String id) {
		UserDTO userDTO = userService.isExistId(id);
		if(userDTO == null) {
			return "not_exist";
		} else {
			return "exist";
		}
	}	
	
	@RequestMapping(value="/user/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestBody Map<String, String> map) { // JSON 형태로 넘겨주는걸 받는 경우
		List<UserDTO> list = userService.userSearch(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("jsonView"); // viewResolver말고 jsonView를 거쳐서 가라(json표기법으로 변환) setViewName("xml에 지정된 id")	
		return mav;
	}
	
//	@RequestMapping(value="/user/search", method = RequestMethod.POST)
//	public ModelAndView search(@RequestParam String option, String keyword) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("option", option);
//		map.put("keyword",keyword);
//		List<UserDTO> list = userService.userSearch(map);
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("list", list);
//		mav.setViewName("jsonView"); // viewResolver말고 jsonView를 거쳐서 가라(json표기법으로 변환) setViewName("xml에 지정된 id")	
//		return mav;
//	}
	
//	json으로 받을 때	
//	@RequestMapping(value="/user/isExistId", method = RequestMethod.POST)
//	public ModelAndView isExistId(@RequestParam String id) {
//		UserDTO userDTO = userService.isExistId(id);	
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("userDTO", userDTO);
//		mav.setViewName("jsonView");
//		return mav;
//	}
	
}

