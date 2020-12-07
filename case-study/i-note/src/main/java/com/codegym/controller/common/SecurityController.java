package com.codegym.controller.common;

import com.codegym.model.Role;
import com.codegym.model.User;
import com.codegym.repository.RoleRepository;
import com.codegym.service.UserService;
import com.codegym.service.impl.UserDetailServiceImpl;
import com.codegym.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;

@Controller
public class SecurityController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping({"/", "/home"})
    public String Homepage(Model model) {
        model.addAttribute("user", userDetailService.getCurrentUser());
        return "welcome";
    }

    @GetMapping("/admin")
    public String adminPage(ModelMap model) {
        model.addAttribute("user", userDetailService.getCurrentUser());
        return "security/admin";
    }

    @GetMapping("/Access_Denied")
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", userDetailService.getCurrentUser());
        return "accessDenied";
    }

    @GetMapping("/dba")
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", userDetailService.getCurrentUser());
        return "security/dba";
    }

    @GetMapping("/login")
    public ModelAndView loginForm() {
        return new ModelAndView("security/login", "user", new User());
    }

    @GetMapping("/register")
    public ModelAndView registerForm() {
        return new ModelAndView("security/register", "user", new User());
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("security/register", "user", user);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
            user.setRoles(roles);
            userService.save(user);
            ModelAndView modelAndView = new ModelAndView("security/register", "user", new User());
            modelAndView.addObject("message", "Successfully registered!");
            return modelAndView;
        }
    }
}
