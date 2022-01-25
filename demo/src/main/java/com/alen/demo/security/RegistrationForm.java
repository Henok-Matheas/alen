package com.alen.demo.security;




import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class RegistrationForm {
    

    private  String username;
    private  String password;
    private  String phone;
    private  String email;
    private String role;
    
    //private  Address address;
    public User toUser(PasswordEncoder encoding){
        return new User(username,encoding.encode(password),phone,email);

    }
    public User toUsering(){
        return new User(username,password,phone,email);

    }
    // @Bean
    // public User toUsering(PasswordEncoder encoding){
    //     return new User(username,encoding.encode(password),phone,email);

    // }
    
    
}
