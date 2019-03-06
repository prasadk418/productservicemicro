package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.domain.Product;
import com.product.domain.User;
import com.product.repository.UserRepository;

@Service
public class UserSerivceImpl implements UserService{

	@Autowired
	private UserRepository repo;

	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public Optional<User> getUser(Integer userId) { 
		return repo.findById(userId);
	}

	@Override
	public User addUser(User user) {		
		return repo.save(user);
	}

	@Override
	public User updateUser(User user) {
		return repo.save(user);
	}

	@Override
	public void deleteUser(Integer userId) {
		repo.deleteById(userId);
		
	}
	
	
}
