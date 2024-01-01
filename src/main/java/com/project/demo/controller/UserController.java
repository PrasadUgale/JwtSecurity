package com.project.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/home")
	public ResponseEntity<?> createAuthenticationToken(){
		return ResponseEntity.ok("Welcome to user Home Page");
	}
	
}
