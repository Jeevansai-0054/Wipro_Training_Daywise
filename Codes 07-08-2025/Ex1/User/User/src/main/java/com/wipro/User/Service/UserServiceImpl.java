package com.wipro.User.Service;

import com.wipro.User.Entity.User;
import com.wipro.User.Repository.UserRepository;
import com.wipro.User.dto.UserNotificationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String NOTIFICATION_URL = "http://localhost:8081/notify";

    @Override
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);

        UserNotificationDto notifyRequest = new UserNotificationDto(
            savedUser.getUsername(),
            savedUser.getPassword(),
            savedUser.getAddress(),
            "Created"
        );

        restTemplate.postForObject(NOTIFICATION_URL, notifyRequest, String.class);
        return savedUser;
    }

    @Override
    public User updateUser(int id, User user) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setAddress(user.getAddress());

            User updatedUser = userRepository.save(existingUser);

            UserNotificationDto notifyRequest = new UserNotificationDto(
                updatedUser.getUsername(),
                updatedUser.getPassword(),
                updatedUser.getAddress(),
                "Updated"
            );

            restTemplate.postForObject(NOTIFICATION_URL, notifyRequest, String.class);
            return updatedUser;
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User userToDelete = userOpt.get();
            userRepository.deleteById(id);

            UserNotificationDto notifyRequest = new UserNotificationDto(
                userToDelete.getUsername(),
                userToDelete.getPassword(),
                userToDelete.getAddress(),
                "Deleted"
            );

            restTemplate.postForObject(NOTIFICATION_URL, notifyRequest, String.class);
        }
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

