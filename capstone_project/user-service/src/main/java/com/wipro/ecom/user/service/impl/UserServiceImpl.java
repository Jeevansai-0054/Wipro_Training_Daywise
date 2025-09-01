package com.wipro.ecom.user.service.impl;

import com.wipro.ecom.user.config.JwtConfig;
import com.wipro.ecom.user.dto.*;
import com.wipro.ecom.user.entity.Role;
import com.wipro.ecom.user.entity.User;
import com.wipro.ecom.user.repository.UserRepository;
import com.wipro.ecom.user.service.UserService;
import com.wipro.ecom.user.exception.ResourceNotFoundException;
import com.wipro.ecom.user.exception.AuthenticationException;
import com.wipro.ecom.user.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;

    @Override
    public UserView registerUser(UserCreateDto userCreateDto) {
        log.info("Registering new user: {}", userCreateDto.getUsername());
        
        if (userRepository.existsByUsername(userCreateDto.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        // Get customer role
        Set<String> customerRole = Set.of(Role.CUSTOMER);

        User user = User.builder()
                .username(userCreateDto.getUsername())
                .email(userCreateDto.getEmail())
                .passwordHash(passwordEncoder.encode(userCreateDto.getPassword()))
                .fullName(userCreateDto.getFullName())
                .address(userCreateDto.getAddress())
                .phone(userCreateDto.getPhone())
                .roles(customerRole)
                .enabled(true)
                .build();

        User savedUser = userRepository.save(user);
        return mapToUserView(savedUser);
    }

    @Override
    public AuthResponse authenticateUser(AuthRequest authRequest) {
        log.info("Authenticating user: {}", authRequest.getUsername());
        
        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new AuthenticationException("Invalid username or password"));
        
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPasswordHash())) {
            throw new AuthenticationException("Invalid username or password");
        }
        
        if (!user.getEnabled()) {
            throw new AuthenticationException("User account is disabled");
        }
        
        String token = jwtConfig.generateToken(user);
        
        return AuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(86400L) // 24 hours in seconds
                .user(mapToUserView(user))
                .build();
    }

    @Override
    public void logout() {
        log.info("User logout requested");
        // In a stateless JWT implementation, logout is typically handled client-side
        // by removing the token. Server-side token invalidation would require additional infrastructure.
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserView> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll().stream()
                .map(this::mapToUserView)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserView getUserById(Long id) {
        log.info("Fetching user by ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapToUserView(user);
    }

    @Override
    public UserView updateUser(UserUpdateDto userUpdateDto) {
        log.info("Updating user: {}", userUpdateDto.getId());
        
        User user = userRepository.findById(userUpdateDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userUpdateDto.getId()));
        
        // Update fields if provided
        if (userUpdateDto.getFullName() != null) {
            user.setFullName(userUpdateDto.getFullName());
        }
        if (userUpdateDto.getAddress() != null) {
            user.setAddress(userUpdateDto.getAddress());
        }
        if (userUpdateDto.getPhone() != null) {
            user.setPhone(userUpdateDto.getPhone());
        }
        if (userUpdateDto.getPassword() != null) {
            user.setPasswordHash(passwordEncoder.encode(userUpdateDto.getPassword()));
        }
        
        User updatedUser = userRepository.save(user);
        return mapToUserView(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with ID: {}", id);
        
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        
        userRepository.deleteById(id);
    }



    @Override
    @Transactional(readOnly = true)
    public List<MenuDto> getUserMenu(Long userId) {
        log.info("Fetching menu for user: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<MenuDto> menu = List.of(
            MenuDto.builder()
                .label("Products")
                .route("/products")
                .rolesAllowed(Set.of("ADMIN", "CUSTOMER"))
                .build(),
            MenuDto.builder()
                .label("Cart")
                .route("/cart")
                .rolesAllowed(Set.of("CUSTOMER"))
                .build(),
            MenuDto.builder()
                .label("My Orders")
                .route("/orders")
                .rolesAllowed(Set.of("CUSTOMER"))
                .build(),
            MenuDto.builder()
                .label("Admin Dashboard")
                .route("/admin/dashboard")
                .rolesAllowed(Set.of("ADMIN"))
                .build(),
            MenuDto.builder()
                .label("Manage Products")
                .route("/admin/products")
                .rolesAllowed(Set.of("ADMIN"))
                .build(),
            MenuDto.builder()
                .label("Manage Users")
                .route("/admin/users")
                .rolesAllowed(Set.of("ADMIN"))
                .build()
        );

        return menu.stream()
                .filter(item -> item.getRolesAllowed().stream()
                        .anyMatch(role -> user.getRoles().stream()
                                .anyMatch(userRole -> userRole.equals(role))))
                .collect(Collectors.toList());
    }

    private UserView mapToUserView(User user) {
        return UserView.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .address(user.getAddress())
                .phone(user.getPhone())
                .roles(user.getRoles())
                .enabled(user.getEnabled())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}