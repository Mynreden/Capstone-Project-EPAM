package com.example.capstoneproject.services;

import com.example.capstoneproject.domain.User;
import com.example.capstoneproject.repositories.UserRepository;
import com.example.capstoneproject.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findById(Long id) {
        try {
            return userRepository.findById(id);
        } catch (Throwable error){
            return Optional.empty();
        }
    }

    public Optional<User> insertUser(String firstname, String lastname, String email, String password, String phone){
        try {
            String securedPassword = passwordEncoder.encode(password);
            User user = userRepository.save(new User(null, firstname, lastname, email, securedPassword, phone));
            return Optional.of(user);
        }
        catch (Throwable error){
            return Optional.empty();
        }
    }

    public boolean authenticate(String email, String rawPassword) {
        try {
            Optional<User> optionalUserDTO = userRepository.findByEmail(email);
            if (optionalUserDTO.isEmpty()){
                return false;
            }
            return passwordEncoder.matches(rawPassword, optionalUserDTO.get().getPassword());
        } catch (Throwable error){
            return false;
        }

    }
    public Optional<User> findByEmail(String email){
        try {
            return userRepository.findByEmail(email);
        } catch (Throwable error){
            return Optional.empty();
        }
    }
}
