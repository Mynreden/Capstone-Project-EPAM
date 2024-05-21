package com.example.capstoneproject.services;

import com.example.capstoneproject.DTO.AdminsDTO;
import com.example.capstoneproject.DTO.UserDTO;
import com.example.capstoneproject.domain.User;
import com.example.capstoneproject.repositories.AdminRepository;
import com.example.capstoneproject.repositories.UserRepository;
import com.example.capstoneproject.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findById(Long id) {
        return dtoToDomain(userRepository.findById(id));
    }

    public boolean isAdmin(Long id){
        Optional<AdminsDTO> admin = adminRepository.findByUserId(id);
        return admin.isPresent();
    }

    private Optional<User> dtoToDomain(Optional<UserDTO> optionalUserDTO){
        if (optionalUserDTO.isEmpty()){
            return Optional.empty();
        }
        UserDTO userDTO = optionalUserDTO.get();
        return Optional.of(new User(userDTO.id, userDTO.firstname, userDTO.lastname, userDTO.email, userDTO.password, userDTO.phone));
    }

    public Optional<User> insertUser(UserDTO userDTO){
        userDTO.password = passwordEncoder.encode(userDTO.password);
        UserDTO savedUserDTO = userRepository.save(userDTO);
        return dtoToDomain(Optional.of(savedUserDTO));
    }

    public boolean authenticate(String email, String rawPassword) {
        Optional<UserDTO> optionalUserDTO = userRepository.findByEmail(email);
        if (optionalUserDTO.isEmpty()){
            return false;
        }
        return passwordEncoder.matches(rawPassword, optionalUserDTO.get().password);
    }
    public Optional<User> findByEmail(String email){
        return dtoToDomain(userRepository.findByEmail(email));
    }
}
