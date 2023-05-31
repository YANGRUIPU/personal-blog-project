package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.BlogContent;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.BlogRepositories;

@Controller
public class BlogContentController {
	@Autowired
	BlogRepositories blogRepositories;
	@Autowired
	AccountRepository accountRepository;

 
	@GetMapping("/blogContent")
	public ModelAndView getblogEditor(ModelAndView mav) {
		UserDetails user = (UserDetails) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
			mav.addObject("name",user.getUsername());
			

		List<BlogContent> blogList2 = blogRepositories.findAll();
		
		
			mav.addObject("blogList", blogList2);
			mav.setViewName("blogContent.html");
			
			
		    return mav;
	}
	
	//削除blog	
	@PostMapping("/delete")
	public String blogDelete(@RequestParam Long id) {
		blogRepositories.deleteById(id);
		return "redirect:/blogContent";
	}
	//修改blog
	@PostMapping("/change")
	public ModelAndView blogChange(@RequestParam Long id, ModelAndView mav) {
		
		BlogContent blogContent = blogRepositories.getById(id);
		mav.addObject("editorId",id);
		mav.addObject("OriginalTitle",blogContent.getBlogTitle() );
		mav.addObject("OriginalSummary", blogContent.getBlogSummary());
		mav.addObject("OriginalContent", blogContent.getBlogContent());
		mav.setViewName("blogEditor.html");
		return mav;
	}
	
	
}
