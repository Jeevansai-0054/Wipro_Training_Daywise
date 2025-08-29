package com.wipro.ecom.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.ecom.user_service.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
}
