package com.project.demo.model;

import java.io.Serializable;

public class JwtResponce implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String jwttoken;
	
	public JwtResponce(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
	public String getToken() {
		return this.jwttoken;
	}
}
