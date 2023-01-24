package com.estock.user.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estock.user.entity.User;
import com.estock.user.service.UserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userService.getAllUsers();
		if(!users.isEmpty()) {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<String>("No record found", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getUserById/{userid}")
	public ResponseEntity<?> getUser(@PathVariable("userid") int userId) {
		Optional<User> user = userService.getOneUser(userId);
		if(user.isPresent()) {
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		}
		return new ResponseEntity<String>("No record found", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/validateToken")
	public ResponseEntity<?> validateBearerToken() {
		return new ResponseEntity<String>("Token is ok", HttpStatus.OK);
	}
}
