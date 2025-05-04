package com.web_project.hoodies.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.web_project.hoodies.model.User;
import com.web_project.hoodies.service.RoleService;
import com.web_project.hoodies.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    public AuthController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userService.isUsernameTaken(user.getUsername())) {
            model.addAttribute("usernameError", "Username is already taken");
            return "register";
        }

        if (userService.isEmailTaken(user.getEmail())) {
            model.addAttribute("emailError", "Email is already registered");
            return "register";
        }

        userService.registerUser(user);
        userService.assignRolesToUser(user, List.of(roleService.findByName("ROLE_USER").getId()));
        return "redirect:/login?registered";
    }
}
