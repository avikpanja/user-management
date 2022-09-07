package com.estock.user.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import com.estock.user.constant.Constants;
import com.estock.user.exception.UnauthorizedException;
import com.estock.user.util.JwtValidator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		
		String authHeader = req.getHeader("Authorization");
		
		Claims claims = JwtValidator.validateBearerToken(authHeader, Constants.SIGNIN_KEY);
		
		req.setAttribute("username", claims);
		
		chain.doFilter(request, response);
	}

}
