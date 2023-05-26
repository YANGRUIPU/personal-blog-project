package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.AccountService2;

@Controller
public class RegisterController {
	@Autowired
	AccountService2 accountService2;

	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}

	@PostMapping("/register")
	public ModelAndView register(@RequestParam String username, @RequestParam String password,
			@RequestParam String confirmPassword, ModelAndView mav) {
		if (accountService2.createAccount(username, password, confirmPassword)) {
			mav.addObject("error", false);
			mav.setViewName("login.html");
		} else {
			mav.addObject("error", true);
			mav.setViewName("register.html");
		}
		return mav;
	}
}
