package com.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.User;


public interface LoginDao extends JpaRepository<User, String> {
	
	public User findByUserName(String userName);
}
