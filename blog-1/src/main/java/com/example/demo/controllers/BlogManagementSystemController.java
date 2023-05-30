package com.example.demo.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogManagementSystemController {
	
@GetMapping("/blogManagementSystem")
public ModelAndView getManagementSystemPage(ModelAndView mav) {
	UserDetails user = (UserDetails) SecurityContextHolder
			.getContext()
			.getAuthentication()
			.getPrincipal();
	
		mav.addObject("name",user.getUsername());
		mav.setViewName("blogManagementSystem.html");
		return mav;
	}
}
