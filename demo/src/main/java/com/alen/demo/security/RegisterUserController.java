package com.alen.demo.security;

import com.alen.demo.RoleRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import com.alen.demo.Role;

@Controller
@RequestMapping("/registerUser")
public class RegisterUserController {
    private RoleRepository roleRepo;
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegisterUserController(UserRepository userRepo, PasswordEncoder passwordEncoder, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    @GetMapping
    public String registerForm(Model model) {
        RegistrationForm form = new RegistrationForm();
        model.addAttribute("form", form);
        return "registerUser";
    }

    @PostMapping
    public String registerProcess(@ModelAttribute("form") @Valid RegistrationForm form, Errors errors) {
        if (errors.hasErrors()) {
            return "registerUser";
        }
        User myUser = form.toUser(passwordEncoder);
        Role userRole = roleRepo.findByName("USER");
        myUser.addRole(userRole);
        userRepo.save(myUser);
        return "redirect:/login";
    }

}
