package com.wipro.User.Controller;

import com.wipro.User.Entity.User;
import com.wipro.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // CREATE
    @PostMapping
    public String createUser(@RequestBody User user) {
        userService.saveUser(user);
        return "User " + user.getUsername() + " is Created";
    }

    // READ ALL
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        userService.updateUser(id, user);
        return "User " + user.getUsername() + " is Updated";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
            return "User " + user.getUsername() + " is Deleted";
        } else {
            return "User not found";
        }
    }
}
