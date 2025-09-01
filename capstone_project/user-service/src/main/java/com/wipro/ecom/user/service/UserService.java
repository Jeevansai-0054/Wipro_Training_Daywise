package com.wipro.ecom.user.service;

import com.wipro.ecom.user.dto.*;

import java.util.List;

public interface UserService {
    UserView registerUser(UserCreateDto userCreateDto);
    AuthResponse authenticateUser(AuthRequest authRequest);
    void logout();
    List<UserView> getAllUsers();
    UserView getUserById(Long id);
    UserView updateUser(UserUpdateDto userUpdateDto);
    void deleteUser(Long id);
    List<MenuDto> getUserMenu(Long userId);
}
