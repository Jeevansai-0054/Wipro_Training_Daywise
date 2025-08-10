package com.wipro.socialmedia.service;

import com.wipro.socialmedia.model.Person;
import com.wipro.socialmedia.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repo;

    public Person savePerson(Person person) {
        return repo.save(person);
    }

    public List<Person> getAllPersons() {
        return repo.findAll();
    }
}