package com.example.capstoneproject.services.interfaces;

import com.example.capstoneproject.domain.User;

import java.util.Optional;

public interface UserServiceInterface {
    public Optional<User> findById(Long id);
    public Optional<User> insertUser(String firstname, String lastname, String email, String password, String phone);
    public boolean authenticate(String username, String rawPassword);
    public Optional<User> findByEmail(String email);
}
