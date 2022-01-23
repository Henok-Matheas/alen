package com.alen.demo.security;

// import org.springframework.security.core.annotation.AuthenticationPrincipal;

// import com.alen.demo.Address;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class RegistrationForm {
    

    private  String username;
    private  String password;
    private  String phone;
    private  String email;
    
    //private  Address address;
    public User toUser(PasswordEncoder encoding){
        return new User(username,encoding.encode(password),phone,email);

    }
    
    
}
