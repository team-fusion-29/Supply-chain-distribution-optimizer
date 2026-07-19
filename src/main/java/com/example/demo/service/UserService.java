package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserByEmail(String email);

    boolean emailExists(String email);

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();

    void deleteUser(Long id);

}