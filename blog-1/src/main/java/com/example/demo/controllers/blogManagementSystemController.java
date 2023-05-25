package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class blogManagementSystemController {
	@GetMapping("/blogManagementSystem")
	public ModelAndView getblogManagementSystem(ModelAndView mav) {
		mav.addObject("name", "Alice");
		mav.setViewName("blogManagementSystem.html");
		return mav;
	}
}
