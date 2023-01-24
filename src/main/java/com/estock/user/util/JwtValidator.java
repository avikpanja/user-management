package com.estock.user.util;

import com.estock.user.constant.Constants;
import com.estock.user.exception.ExpiredTokenException;
import com.estock.user.exception.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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
		
		if(bearerToken == null || !bearerToken.startsWith("Bearer ")) {
			throw new InvalidTokenException("Missing or invalid JWT");
		}
		String jwtToken = bearerToken.substring(7);
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(Constants.SIGNIN_KEY).parseClaimsJws(jwtToken).getBody();
		} catch(Exception e) {
			if(e instanceof ExpiredJwtException) {
				throw new ExpiredTokenException("JWT is expired", e);
			} else {
				throw new InvalidTokenException("JWT is invalid", e);
			}
		}
		return claims;
	}
}
