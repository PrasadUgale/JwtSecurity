package com.project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.model.JwtRequest;
import com.project.demo.model.JwtResponce;
import com.project.demo.model.User;
import com.project.demo.service.JwtTokenUtil;
import com.project.demo.service.LoginService;



@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authRequest) throws Exception{
		loginService.authenticate(authRequest.getUsername(), authRequest.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponce(token));
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
		if(loginService.saveUser(user))
			return "SUCCESS";
		return "FAIL";
	}
}
