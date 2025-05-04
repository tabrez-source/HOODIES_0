package com.web_project.hoodies.controller;

import com.web_project.hoodies.model.Contact;
import com.web_project.hoodies.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContactForm(@Valid Contact contact, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "contact";
        }
        contactRepository.save(contact);
        model.addAttribute("message", "Your message has been submitted successfully!");
        return "contact";
    }
}
