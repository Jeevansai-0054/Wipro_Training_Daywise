package com.wipro.socialmedia.model;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
}