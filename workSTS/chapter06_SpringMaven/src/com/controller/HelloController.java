package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller // @Component대신
public class HelloController {
	@RequestMapping(value="/hello.do", method=RequestMethod.GET) // url로 매칭되기 때문에 오버로드X
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result","Hello Spring!");
//		mav.setViewName("hello");
		mav.setViewName("/view/hello");
		return mav;
	}
}
