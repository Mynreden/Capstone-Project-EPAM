package com.example.capstoneproject.controllers;

import com.example.capstoneproject.domain.User;
import com.example.capstoneproject.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        session = new MockHttpSession();
    }

    @Test
    public void testRegistration_Success() throws Exception {
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password", "1234567890");
        when(userService.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userService.insertUser(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(Optional.of(user));

        mockMvc.perform(post("/users/registration")
                        .param("email", "john.doe@example.com")
                        .param("password", "password")
                        .param("firstname", "John")
                        .param("lastname", "Doe")
                        .param("phone", "1234567890")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attributeCount(0));

        assertEquals(user, session.getAttribute("user"));
    }

    @Test
    public void testRegistration_EmailTaken() throws Exception {
        when(userService.findByEmail(anyString())).thenReturn(Optional.of(new User()));

        mockMvc.perform(post("/users/registration")
                        .param("email", "john.doe@example.com")
                        .param("password", "password")
                        .param("firstname", "John")
                        .param("lastname", "Doe")
                        .param("phone", "1234567890")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/sign_in"))
                .andExpect(flash().attribute("message", "This email is already taken. Please try again."));

        assertNull(session.getAttribute("user"));
    }

    @Test
    public void testRegistration_Failure() throws Exception {
        when(userService.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userService.insertUser(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(Optional.empty());

        mockMvc.perform(post("/users/registration")
                        .param("email", "john.doe@example.com")
                        .param("password", "password")
                        .param("firstname", "John")
                        .param("lastname", "Doe")
                        .param("phone", "1234567890")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/sign_in"))
                .andExpect(flash().attribute("message", "Registration failed. Please try again."));

        assertNull(session.getAttribute("user"));
    }

    @Test
    public void testLogin_Success() throws Exception {
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password", "1234567890");
        when(userService.authenticate(anyString(), anyString())).thenReturn(true);
        when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));

        mockMvc.perform(post("/users/login")
                        .param("email", "john.doe@example.com")
                        .param("password", "password")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attributeCount(0));

        assertEquals(user, session.getAttribute("user"));
    }

    @Test
    public void testLogin_Failure() throws Exception {
        when(userService.authenticate(anyString(), anyString())).thenReturn(false);

        mockMvc.perform(post("/users/login")
                        .param("email", "john.doe@example.com")
                        .param("password", "password")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/sign_in"))
                .andExpect(flash().attribute("message", "Login failed. Please try again."));

        assertEquals(null, session.getAttribute("user"));
    }

    @Test
    public void testLogout() throws Exception {
        session.setAttribute("user", new User());

        mockMvc.perform(get("/users/logout")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/sign_in"));

        assertEquals(null, session.getAttribute("user"));
    }
}
