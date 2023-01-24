package com.estock.user.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estock.user.constant.Constants;
import com.estock.user.entity.User;
import com.estock.user.exception.ExpiredTokenException;
import com.estock.user.exception.UnauthorizedException;
import com.estock.user.model.LoginCredential;
import com.estock.user.model.UserContext;
import com.estock.user.service.KafkaPublisher;
import com.estock.user.service.UserService;
import com.estock.user.util.JwtValidator;
import com.estock.user.util.UserContextUtil;
import com.estock.user.util.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth/v1/user")
//@CrossOrigin("*")
public class UserAuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private KafkaPublisher publisher;
	
	@PostMapping(value="/addUser", consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> registerNewUser(@RequestBody User user) {
		if(userService.addUser(user)!=null) {
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Registration unsuccessful", HttpStatus.CONFLICT); 
	}
	
	@PostMapping(value="/login", consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> login(@RequestBody LoginCredential loginCred) {
		
		try {
			User user = validateAndGetUser(loginCred.getUsername(), loginCred.getPassword());
			String token = generateToken(loginCred.getUsername());
			UserContext uc = UserContextUtil.prepareUserContext(user, token);
			return new ResponseEntity<UserContext>(uc,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(Map.of("message", "Invalid credential"), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping(value="/validateToken") 
	public ResponseEntity<?> validateBearerToken(@RequestHeader("Authorization") String authHeader) {
		try {
			Claims claims = JwtValidator.validateBearerToken(authHeader, Constants.SIGNIN_KEY);
			//this.publisher.publish("200 - Token validation successful");
			return new ResponseEntity<Claims>(claims,HttpStatus.OK);
		} catch (Exception e) {
			if(!(e instanceof ExpiredTokenException)) {
				e.printStackTrace();
			}
			return new ResponseEntity<Map<String,String>>(Map.of("message", e.getMessage()), HttpStatus.UNAUTHORIZED);
		}
	}
	
	private User validateAndGetUser(final String username, final String password) throws Exception {
		if(Util.isEmpty(username) || Util.isEmpty(password)) {
			throw new Exception("Username or Password should not be empty");
		}
		return userService.getUser(username, password);
	}
	
	private String generateToken(String username) throws Exception {
		
		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+2000))
				.signWith(SignatureAlgorithm.HS256, Constants.SIGNIN_KEY)
				.compact();
		
		return token;
	}
}
