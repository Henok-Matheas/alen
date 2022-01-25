package com.alen.demo.security;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registerUser")
public class RegisterUserController {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegisterUserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("message", null);
        return "registerUser";
    }

    @PostMapping
    public String registerProcess(@Valid RegistrationForm form, Errors errors) {
        if (errors.hasErrors()) {
            return "registerUser";
        }
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
