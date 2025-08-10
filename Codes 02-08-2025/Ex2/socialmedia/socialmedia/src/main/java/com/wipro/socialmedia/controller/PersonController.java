package com.wipro.socialmedia.controller;

import com.wipro.socialmedia.model.Person;
import com.wipro.socialmedia.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return service.savePerson(person);
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return service.getAllPersons();
    }
}