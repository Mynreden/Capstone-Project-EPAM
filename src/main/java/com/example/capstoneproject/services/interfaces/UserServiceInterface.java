package com.example.capstoneproject.services.interfaces;

import com.example.capstoneproject.DTO.UserDTO;
import com.example.capstoneproject.domain.User;

import java.util.Optional;

public interface UserServiceInterface {
    public Optional<User> findById(Long id);
    public boolean isAdmin(Long id);
    public Optional<User> insertUser(UserDTO userDTO);
    public boolean authenticate(String username, String rawPassword);
    public Optional<User> findByEmail(String email);
}
