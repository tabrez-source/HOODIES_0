package com.web_project.hoodies.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "The application is running successfully!";
    }
    
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "<html><body>" +
               "<h1>Welcome to Hoodies Store!</h1>" +
               "<p>The application is running successfully!</p>" +
               "<p>Visit <a href='/test'>/test</a> to see a simple API response.</p>" +
               "</body></html>";
    }
} 