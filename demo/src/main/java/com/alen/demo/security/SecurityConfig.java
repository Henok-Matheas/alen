package com.alen.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null)
                return user;
            throw new UsernameNotFoundException("user '" + username + " ' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/admin", "/listOfPharmacyAdmin", "/listOfUserAdmin", "/registerAdmin",
                        "/deleteUserAdmin/", "/deletePharmacyAdmin/", "/updatePharmacyAdmin/", "/updateUserAdmin")
                .hasAnyAuthority("ADMIN")
                .antMatchers("/search", "/user", "/user/", "/pharmacy/", "/medicine/", "pharmacy/", "user/")
                .hasAnyAuthority("USER", "ADMIN")

                .antMatchers("/", "/registerUser", "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .build();
    }

}