package com.product.service;

import java.util.List;
import java.util.Optional;

import com.product.domain.Product;
import com.product.domain.User;

public interface UserService {
	public List<User> getAllUsers();
	public Optional<User> getUser(Integer userId);
	public User addUser(User user);
	public User updateUser(User user);
	public void deleteUser(Integer userId);		
}
