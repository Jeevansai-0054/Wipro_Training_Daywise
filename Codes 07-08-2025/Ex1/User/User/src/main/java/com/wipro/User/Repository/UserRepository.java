package com.wipro.User.Repository;

import com.wipro.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // No need to write any methods — JpaRepository gives all CRUD methods
}

