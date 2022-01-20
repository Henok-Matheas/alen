package com.alen.demo;

import com.alen.demo.security.UserRepository;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public class UserController {
    private UserRepository userRepo;
    private String Id;
    @GetMapping
    public String userPage(Model model){
        model.addAttribute("userinfo",this.userRepo.findById(Id));      
        return "user";
    }
    // @PostMapping
    // public String redirectForm(){
    //     return "redirect:/userForm";
    // }
    
}
