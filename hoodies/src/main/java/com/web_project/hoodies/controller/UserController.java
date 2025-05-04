package com.web_project.hoodies.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web_project.hoodies.model.User;
import com.web_project.hoodies.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
       this.userService = userService;
    }

    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
       User user = userService.findByUsername(authentication.getName());
       model.addAttribute("user", user);
       return "profile";
    }

    @PostMapping("/profile")
    public String updateUserProfile(@Valid @ModelAttribute("user") User user,
                                    BindingResult bindingResult,
                                    Authentication authentication,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "profile";
        }

        User existingUser = userService.findByUsername(authentication.getName());
        existingUser.setEmail(user.getEmail());
        // Update additional fields as needed

        userService.saveUser(existingUser); // Ensure this method updates the user
        model.addAttribute("successMessage", "Profile updated successfully!");
        return "profile";
    }
}
