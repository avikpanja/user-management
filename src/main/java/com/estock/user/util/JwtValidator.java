package com.estock.user.util;

import javax.servlet.ServletException;

import com.estock.user.constant.Constants;
import com.estock.user.exception.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtValidator {

	/**
	 * Pass the value Authorization header value to bearerToken and signed key to signinKey
	 * 
	 * @param key
	 * @param header
	 * @return io.jsonwebtoken.Claims
	 */
	public static Claims validateBearerToken(String bearerToken, String signinKey) {
		
		if(bearerToken == null || !bearerToken.startsWith("Bearer")) {
			throw new RuntimeException("Missing or invalid authorization header");
		}
		String jwtToken = bearerToken.substring(7);
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(Constants.SIGNIN_KEY).parseClaimsJws(jwtToken).getBody();
		} catch(Exception e) {
			throw new UnauthorizedException();
		}
		return claims;
	}
}
