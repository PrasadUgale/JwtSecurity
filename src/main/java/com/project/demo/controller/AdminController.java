package com.project.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/home")
	public ResponseEntity<?> createAuthenticationToken(){
		return ResponseEntity.ok("Welcome to admin Home Page");
	}
	
}
