package com.project.demo.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.demo.service.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		  final String requestTokenHeader = request.getHeader("Authorization");
		  
		  String username = null;
		  String jwtToken = null;
		  
		  if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			  jwtToken = requestTokenHeader.substring(7);
			  try {
				  username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			  } catch( IllegalArgumentException e) {
				  System.out.println("Illegal Argument");
			  } catch(ExpiredJwtException e) {
				  System.out.println("Jwt Expired");
			  }
		  } else {
			  System.out.println("Invalid Token");
		  }
		  
		  if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			  UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			  
			  if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				  usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				  SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			  }
		  }
		  filterChain.doFilter(request, response);
	}
	
}
