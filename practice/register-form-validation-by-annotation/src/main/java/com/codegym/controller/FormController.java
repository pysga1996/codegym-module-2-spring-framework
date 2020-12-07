package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class FormController {

    private final UserService userService;

    @Autowired
    public FormController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "user/index";
    }

    @PostMapping("/")
    public String checkValidation(@Valid @ModelAttribute("user") User user,
        BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("user", user);
            return "user/index";
        } else {
            userService.save(user);
            model.addAttribute("user", user);
            return "user/result";
        }
    }
}
