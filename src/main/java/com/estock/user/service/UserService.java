package com.estock.user.service;

import java.util.List;
import java.util.Optional;

import com.estock.user.entity.User;

public interface UserService {

	List<User> getAllUsers();
	Optional<User> getOneUser(int userId);
	User addUser(User user);
	
	User getUser(String username, String password);
}
