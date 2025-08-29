package com.wipro.ecom.user_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.wipro.ecom.user_service.Entity.User;
import com.wipro.ecom.user_service.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository repo;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository repo, PasswordEncoder passwordEncoder)
	{
		this.repo=repo;
		this.passwordEncoder = passwordEncoder;
	}
	 public User saveUser(User user)
	 {
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		 return repo.save(user);
		 
	 }
	 public List<User> getAllUsers()
	 {
		 return repo.findAll();
	 }
	 public Optional<User> getUserById(Long id)
	 {
		 return repo.findById(id);
	 }
	 public void deleteUser(Long id)
	 {
		 
		 repo.deleteById(id);
	 }
	 public User updateUser(User user)
	 {
		 return repo.save(user);
	 }

}
