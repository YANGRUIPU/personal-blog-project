package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Blog_Content")
public class BlogContent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String blogTitle;
	@Column
	private String blogSummary;
	@Column
	private String blogContent;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public BlogContent(String blogTitle, String blogSummary, String blogContent) {
		super();
		this.blogTitle = blogTitle;
		this.blogSummary = blogSummary;
		this.blogContent = blogContent;
	}

	public BlogContent() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogSummary() {
		return blogSummary;
	}

	public void setBlogSummary(String blogSummary) {
		this.blogSummary = blogSummary;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	@Override
	public String toString() {
		return "BlogContent [id=" + id + ", blogTitle=" + blogTitle + ", blogSummary=" + blogSummary + ", blogContent="
				+ blogContent + "]";
	}

}
