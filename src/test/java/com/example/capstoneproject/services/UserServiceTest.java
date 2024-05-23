package com.example.capstoneproject.services;

import com.example.capstoneproject.domain.User;
import com.example.capstoneproject.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById_UserExists() {
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password", "1234567890");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testFindById_UserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    public void testInsertUser_Success() {
        User user = new User(null, "Jane", "Doe", "jane.doe@example.com", "securePassword", "0987654321");
        when(passwordEncoder.encode("securePassword")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(new User(1L, "Jane", "Doe", "jane.doe@example.com", "encodedPassword", "0987654321"));

        Optional<User> result = userService.insertUser("Jane", "Doe", "jane.doe@example.com", "securePassword", "0987654321");
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("encodedPassword", result.get().getPassword());
    }

    @Test
    public void testInsertUser_Failure() {
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException());

        Optional<User> result = userService.insertUser("Jane", "Doe", "jane.doe@example.com", "securePassword", "0987654321");
        assertFalse(result.isPresent());
    }

    @Test
    public void testAuthenticate_Success() {
        User user = new User(1L, "Jane", "Doe", "jane.doe@example.com", "encodedPassword", "0987654321");
        when(userRepository.findByEmail("jane.doe@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("securePassword", "encodedPassword")).thenReturn(true);

        boolean result = userService.authenticate("jane.doe@example.com", "securePassword");
        assertTrue(result);
    }

    @Test
    public void testAuthenticate_Failure() {
        when(userRepository.findByEmail("jane.doe@example.com")).thenReturn(Optional.empty());

        boolean result = userService.authenticate("jane.doe@example.com", "securePassword");
        assertFalse(result);
    }

    @Test
    public void testFindByEmail_UserExists() {
        User user = new User(1L, "Jane", "Doe", "jane.doe@example.com", "encodedPassword", "0987654321");
        when(userRepository.findByEmail("jane.doe@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail("jane.doe@example.com");
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testFindByEmail_UserDoesNotExist() {
        when(userRepository.findByEmail("jane.doe@example.com")).thenReturn(Optional.empty());

        Optional<User> result = userService.findByEmail("jane.doe@example.com");
        assertFalse(result.isPresent());
    }
}
