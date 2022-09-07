package com.estock.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estock.user.entity.User;
import com.estock.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public User addUser(User user) {
		return repo.saveAndFlush(user);
	}

	@Override
	public Optional<User> getOneUser(int userId) {
		return repo.findById(userId);
	}

	@Override
	public User getUser(String username, String password) {
		return repo.validateUser(username, password);
	}

}
