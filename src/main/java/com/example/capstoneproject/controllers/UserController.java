package com.example.capstoneproject.controllers;

import com.example.capstoneproject.domain.User;
import com.example.capstoneproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/registration")
    public RedirectView registration(@RequestParam String email,
                                     @RequestParam String password,
                                     @RequestParam String firstname,
                                     @RequestParam String lastname,
                                     @RequestParam String phone,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()){
            redirectAttributes.addFlashAttribute("message", "This email is already taken. Please try again.");
            return new RedirectView("/sign_in");
        }
        Optional<User> optionalUser = userService.insertUser( firstname, lastname, email, password, phone );
        if (optionalUser.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Registration failed. Please try again.");
            return new RedirectView("/sign_in");
        }

        User user = optionalUser.get();
        session.setAttribute("user", user);

        return new RedirectView("/");
    }

    @PostMapping("/login")
    public RedirectView login(@RequestParam String email,
                              @RequestParam String password,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        if (!userService.authenticate(email, password)){
            redirectAttributes.addFlashAttribute("message", "Login failed. Please try again.");
            return new RedirectView("/sign_in");
        }

        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Login failed. Please try again.");
            return new RedirectView("/sign_in");
        }

        User user = optionalUser.get();
        session.setAttribute("user", user);

        session.setAttribute("isAdmin", userService.isAdmin(user));

        return new RedirectView("/");
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("isAdmin");

        return new RedirectView("/sign_in");
    }
}
