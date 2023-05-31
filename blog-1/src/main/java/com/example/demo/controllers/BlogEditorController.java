package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Account;
import com.example.demo.models.BlogContent;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.BlogRepositories;

@Controller
public class BlogEditorController {
	@Autowired
	BlogRepositories blogRepositories;
	@Autowired
	AccountRepository accountRepository;

	@GetMapping("/blogEditor")
	public String getblogEditor() {
		return "blogEditor.html";

	}
//存储博客数据进数据库
	@PostMapping("/blogEditor")
	public ModelAndView blogEditor(@RequestParam(required=false)  Long id,@RequestParam String blogTitle, @RequestParam String blogSummary,
			@RequestParam String blogContent, ModelAndView mav) {
		//添加内容
		if(id==null) {
			UserDetails user = (UserDetails) SecurityContextHolder
					.getContext()
					.getAuthentication()
					.getPrincipal();
			
			Account account=accountRepository.findByUsername(user.getUsername());
			
			
		blogRepositories.save(new BlogContent(blogTitle, blogSummary, blogContent,account));
		
		mav.setViewName("redirect:/blogContent");
		//修改内容
		}else {
			BlogContent blogContent2=blogRepositories.getById(id);
			blogContent2.setBlogTitle(blogTitle);
			blogContent2.setBlogContent(blogContent);
			blogContent2.setBlogSummary(blogSummary);
			blogRepositories.save(blogContent2);
			
			mav.setViewName("redirect:/blogContent");
		}
		return mav;
	}

}
