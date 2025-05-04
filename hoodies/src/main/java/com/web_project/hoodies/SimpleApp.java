package com.web_project.hoodies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SimpleApp {
    
    public static void main(String[] args) {
        SpringApplication.run(SimpleApp.class, args);
    }
    
    @GetMapping("/home")
    public String home() {
        return "<html><body>" +
               "<h1 style='color: green; text-align: center; margin-top: 100px;'>Success!</h1>" +
               "<h2 style='text-align: center;'>The Hoodies Store Application is Running</h2>" +
               "<p style='text-align: center;'>This is a simple page showing that the application is running successfully.</p>" +
               "</body></html>";
    }
    
    @GetMapping("/api/status")
    public String status() {
        return "{ \"status\": \"UP\", \"message\": \"The application is running successfully!\" }";
    }
} 