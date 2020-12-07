package com.codegym.validator;

import com.codegym.model.User;
import com.codegym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class UserValidator implements Validator {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User userToCheck = (User) target;
        List<User> userList = userRepository.findAll();
        boolean isExist = false;
        for (User user: userList) {
            if (user.getUsername().equals(userToCheck.getUsername())) {
                isExist = true;
                break;
            }
        }
        if (isExist) {
            errors.rejectValue("username", "username.exist");
        }
    }
}
