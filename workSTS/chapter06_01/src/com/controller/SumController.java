package com.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bean.SumDTO;

import lombok.Setter;

@Controller
@Setter
public class SumController {
	private SumDTO sumDTO;
	
//	@RequestMapping(value="/input.do", method=RequestMethod.GET)
//	public ModelAndView input() {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/sum/input");
//		return mav;
//	}
	
	@RequestMapping(value="/input.do", method=RequestMethod.GET)
	// 리턴 타입이 String이면 단순 문자열이 아니라 view의 이름으로 인식한다
	// view의 이름이 아니라 단순 문자열로 리턴하려면 @ResponseBodt를 써야한다
	public String input() {
		return "/sum/input";
	}
	
//-------------------------------------------------------------------------------------------------	
	
//	@RequestMapping(value="/result.do", method=RequestMethod.GET)
//	public ModelAndView result() {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/sum/result");
//		return mav;
//	}
	
//	@RequestMapping(value="/result.do", method=RequestMethod.GET)
//	public ModelAndView result(@RequestParam int x, @RequestParam int y) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("x", x);
//		mav.addObject("y", y);
//		mav.setViewName("/sum/result");
//		return mav;
//	}
	
//	@RequestMapping(value="/result.do", method=RequestMethod.GET)
//	public ModelAndView result(@RequestParam(required=false, defaultValue="0") String x, 
//			                   @RequestParam(required=false, defaultValue="0") String y) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("x", x);
//		mav.addObject("y", y);
//		mav.setViewName("/sum/result");
//		return mav;
//	}
	
//	@RequestMapping(value="/result.do", method=RequestMethod.GET)
//	public String result(@RequestParam Map<String, String> map, ModelMap modelMap) {
//		modelMap.put("x", map.get("x"));
//		modelMap.put("y", map.get("y"));
//		return "/sum/result";
//	}
	
	@RequestMapping(value="/result.do", method=RequestMethod.GET)
	public String result(@ModelAttribute SumDTO sumDTO, Model model) {
		model.addAttribute("sumDTO", sumDTO);
		return "/sum/result";
	}
}
