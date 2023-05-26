package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.models.BlogContent;
import com.example.demo.repositories.BlogRepositories;

@Controller
public class BlogEditorController {
	@Autowired
	BlogRepositories blogRepositories;

	@GetMapping("/blogEditor")
	public String getblogEditor() {
		return "blogEditor.html";

	}
//存储博客数据进数据库
	@PostMapping("/blogEditor")
	public ModelAndView blogEditor(@RequestParam String blogTitle, @RequestParam String blogSummary,
			@RequestParam String blogContent, ModelAndView mav) {
		blogRepositories.save(new BlogContent(blogTitle, blogSummary, blogContent));
		mav.addObject("blogTitle2", blogTitle);
		mav.addObject("blogSummary2", blogSummary);
		mav.setViewName("blogManagementSystem.html");
		return mav;
	}

}
