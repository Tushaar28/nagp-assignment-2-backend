package com.nagp.tushaar.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.tushaar.service.BlogService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class Controller {
	
	@Autowired BlogService service;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveBlog(@RequestBody Map<String, String> body) {
		return service.saveBlog(body);
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<?> getAllBlogs() {
		return service.getAllBlogs();
	}

}
