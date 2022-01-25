package com.alen.demo;

import com.alen.demo.security.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.ArrayList;

import com.alen.demo.security.*;
@Controller
@RequestMapping("/listOfUserAdmin")
@RequiredArgsConstructor
public class listOfUserAdminController {
    private final UserRepository userRepo;
    @GetMapping
    public String retrunUserList(Model model){
        List<User> user=new ArrayList<>();
        this.userRepo.findAll().forEach(i->user.add(i));
        model.addAttribute("user", user);
        
        return "listOfUser";
    }
    
}
