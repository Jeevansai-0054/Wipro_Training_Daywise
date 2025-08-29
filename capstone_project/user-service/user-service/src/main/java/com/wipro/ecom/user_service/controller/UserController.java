package com.wipro.ecom.user_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecom.user_service.Entity.User;
import com.wipro.ecom.user_service.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService service;
	
	public UserController (UserService service)
	{
		this.service = service;
	}
	
	@PostMapping("/create")  
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = service.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id)
	{
		return service.getUserById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = service.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
