package com.alen.demo;

import com.alen.demo.security.User;
import com.alen.demo.security.UserRepository;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class inTheHomeController {
    private UserRepository userRepo;
    @GetMapping("/inTheHome")
    public String viewHomeing(Model model,@AuthenticationPrincipal User loggedUser){
        model.addAttribute("model", loggedUser);
        return "inTheHome";
    }
    @GetMapping("/inThePharmacy")
    public String viewpharmacy(){
        return "inThePharmacy";
    }
    
}
