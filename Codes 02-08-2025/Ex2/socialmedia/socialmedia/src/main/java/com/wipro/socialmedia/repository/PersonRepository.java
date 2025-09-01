package com.wipro.socialmedia.repository;

import com.wipro.socialmedia.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}