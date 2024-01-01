package com.project.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.demo.dao.LoginDao;
import com.project.demo.model.User;


@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	public void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			throw new Exception("Wrong Credentials");
		}  
	}
	
	public List<User> getUsers(){
		return loginDao.findAll();
	}
	
	public boolean saveUser(User user) {
		user.setRole("ROLE_USER");
		user.setPassword(encoder.encode(user.getPassword()));
		if(loginDao.save(user) != null)
			return true;
		return false;
	}
	
	

}

