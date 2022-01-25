package com.alen.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class loginController {
    @GetMapping("/login")
    public String displayLogin(){
        return "login";
    }
    // @GetMapping ("/Pharmacy_login")
    // public String displayLoginPharmacy(){
    //     return "loginPharmacy";
    // }
    
}
