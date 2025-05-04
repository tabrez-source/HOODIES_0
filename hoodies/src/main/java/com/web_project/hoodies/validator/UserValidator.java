package com.web_project.hoodies.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.web_project.hoodies.model.User;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (user.getPassword() != null && user.getConfirmPassword() != null) {
            if (!user.getPassword().equals(user.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "password.mismatch", "Passwords do not match");
            }
        }
    }
}
