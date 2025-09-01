package com.wipro.User.Service;

import com.wipro.User.Entity.User;
import java.util.List;

public interface UserService {

    User saveUser(User user);
    User updateUser(int id, User user);
    void deleteUser(int id);
    User getUserById(int id);
    List<User> getAllUsers();
}

