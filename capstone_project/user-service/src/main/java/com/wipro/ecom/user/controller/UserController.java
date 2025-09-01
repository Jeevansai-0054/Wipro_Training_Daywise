package com.wipro.ecom.user.controller;

import com.wipro.ecom.user.dto.*;
import com.wipro.ecom.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserView> registerUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        log.info("Registering new user: {}", userCreateDto.getUsername());
        UserView user = userService.registerUser(userCreateDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        log.info("User login attempt: {}", authRequest.getUsername());
        AuthResponse response = userService.authenticateUser(authRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        log.info("User logout");
        userService.logout();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserView>> getAllUsers() {
        log.info("Fetching all users");
        List<UserView> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal")
    public ResponseEntity<UserView> getUserById(@PathVariable Long id) {
        log.info("Fetching user by ID: {}", id);
        UserView user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or #userUpdateDto.id == authentication.principal")
    public ResponseEntity<UserView> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        log.info("Updating user: {}", userUpdateDto.getId());
        UserView user = userService.updateUser(userUpdateDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Deleting user: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<List<MenuDto>> getUserMenu(@PathVariable Long id) {
        log.info("Fetching menu for user: {}", id);
        List<MenuDto> menu = userService.getUserMenu(id);
        return ResponseEntity.ok(menu);
    }
}
