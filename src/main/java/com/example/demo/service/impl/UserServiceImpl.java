package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    // ==========================================
    // Repository
    // ==========================================

    private final UserRepository userRepository;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    // ==========================================
    // Save User
    // ==========================================

    @Override
    public User saveUser(User user) {

        return userRepository.save(user);

    }

    // ==========================================
    // Find User By Email
    // ==========================================

    @Override
    public Optional<User> getUserByEmail(String email) {

        return userRepository.findByEmail(email);

    }

    // ==========================================
    // Check Email Exists
    // ==========================================

    @Override
    public boolean emailExists(String email) {

        return userRepository.existsByEmail(email);

    }

    // ==========================================
    // Find User By ID
    // ==========================================

    @Override
    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);

    }

    // ==========================================
    // Get All Users
    // ==========================================

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    // ==========================================
    // Delete User
    // ==========================================

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);

    }

}