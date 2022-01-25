package com.alen.demo;

import com.alen.demo.security.User;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewUserController {
    @GetMapping(value="/user")
    public String getUserProfile(Model model,@AuthenticationPrincipal User loggedUser) {
        model.addAttribute("user", loggedUser);
        return "user";
    }
    
}
