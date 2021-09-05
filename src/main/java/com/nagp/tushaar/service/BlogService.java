package com.nagp.tushaar.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.nagp.tushaar.model.Blog;
import com.nagp.tushaar.repository.BlogRepository;
import com.nagp.tushaar.util.BlogGenerate;

@Service
public class BlogService {
	
	@Autowired BlogRepository repository;
	
	@Autowired Gson gson;

	public ResponseEntity<?> saveBlog(Map<String, String> body) {
		try {
			String id = body.get("id");
			String title = body.get("title");
			String description = body.get("description");
			Blog blog = new Blog();
			blog.setId(id);
			Blog prevBlog = repository.findById(id).orElse(null);
			if (prevBlog == null)
				blog.setDate(new Date());
			else
				blog.setDate(prevBlog.getDate());
			blog.setDescription(description);
			blog.setTitle(title);
			repository.save(blog);
			return new ResponseEntity<Object>("Blog saved", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getAllBlogs() {
		try {
			List<Blog> blogs = repository.findAll();
			blogs.sort((b1, b2) -> b2.getDate().compareTo(b1.getDate()));
			JsonArray res = new JsonArray();
			for (Blog blog: blogs) {
				res.add(BlogGenerate.generateJson(blog));
			}
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
