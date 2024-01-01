package com.project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.demo.dao.LoginDao;
import com.project.demo.model.User;
import com.project.demo.model.UserPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private LoginDao loginDao; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = loginDao.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("No User with given credentials found!!");
		}
		return new UserPrincipal(user);
	}
	

}
