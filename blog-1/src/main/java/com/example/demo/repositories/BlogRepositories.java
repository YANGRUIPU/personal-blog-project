package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.BlogContent;

public interface BlogRepositories extends JpaRepository<BlogContent, Long> {
	BlogContent findByBlogTitle(String blogTitle);

}
