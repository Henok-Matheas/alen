package com.alen.demo;

import com.alen.demo.security.RegistrationForm;
import com.alen.demo.security.User;
import com.alen.demo.security.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registerAdmin")
public class registerAdminController {
    private RoleRepository roleRepo;
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    public registerAdminController(UserRepository userRepo,PasswordEncoder passwordEncoder,RoleRepository roleRepo){
        this.userRepo=userRepo;
        this.passwordEncoder=passwordEncoder;
        this.roleRepo=roleRepo;
    }
    @GetMapping
    public String registerAdmin(){
        return "registerAdmin";
    }
    
    @PostMapping
    public String processAdmin(RegistrationForm form){
        User myUser=form.toUser(passwordEncoder);
        Role userRole=roleRepo.findByName("ADMIN");
        myUser.addRole(userRole);

        userRepo.save(myUser);
        return "redirect:/admin";
    }
}
