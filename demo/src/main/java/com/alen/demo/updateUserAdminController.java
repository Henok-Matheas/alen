package com.alen.demo;

import com.alen.demo.security.RegistrationForm;
import com.alen.demo.security.User;
import com.alen.demo.security.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller

public class updateUserAdminController {
    PasswordEncoder passwordEncoder;
    UserRepository userRepo;
    RoleRepository roleRepo;
    public updateUserAdminController(PasswordEncoder passwordEncoder,UserRepository userRepo,RoleRepository roleRepo){
        this.passwordEncoder=passwordEncoder;
        this.userRepo=userRepo;
        this.roleRepo=roleRepo;
    }
    @GetMapping("/updateUserAdmin/{id}")
    public String updateUser(Model model, @PathVariable Integer id){
        User myUser=userRepo.findById(id);
        model.addAttribute("user",myUser);

        return "updateUserAdmin";
    }

    @PostMapping("/updateUserAdmin/{id}")
    public String changeUser(@PathVariable Integer id,RegistrationForm form){
        User myUserRepo=userRepo.findById(id);
        form.setPassword(myUserRepo.getPassword());
         User myUser=form.toUsering();
         myUser.setId(id);
         userRepo.delete(userRepo.findById(id));
         Role userRole=roleRepo.findByName("USER");
         myUser.addRole(userRole);
         userRepo.save(myUser);

        return "redirect:/listOfUser";
    }
    @PostMapping("/deleteUserAdmin/{id}")
    public String deleteUser(@PathVariable Integer id){
        User myUserRepo=userRepo.findById(id);
       // userRepo.deleteById(id);
        return "listOfUser";
    }

    
}
