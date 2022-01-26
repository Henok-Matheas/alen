package com.alen.demo.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationForm {

    @NotBlank
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String username;
    @NotNull
    @Size(min = 6, message = "name must be at least 6 characters long")
    private String password;

    private String phone;

    private String role;

    // private Address address;
    public User toUser(PasswordEncoder encoding) {
        return new User(username, encoding.encode(password), phone);

    }

    public User toUsering() {
        return new User(username, password, phone);

    }
    // @Bean
    // public User toUsering(PasswordEncoder encoding){
    // return new User(username,encoding.encode(password),phone,email);

    // }

}
