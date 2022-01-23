package com.alen.demo.security;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AccessLevel;
import com.alen.demo.Role;


@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE,force=true)
@RequiredArgsConstructor


public class User implements UserDetails {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@NotBlank
   // @Size(min = 5, message = "Name must be at least 5 characters long")
    private final String username;
    private final String password;

    private final String phone;

   // @NotNull
    private final String email;
    // new
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles=new HashSet<>();
    public User(String username,String password,String phone,String email,Set<Role> roles){
        this.username=username;
        this.password=password;
        this.phone=phone;
        this.email=email;
        this.roles=roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }


   // @OneToOne
    //private final Address address;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for(Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));

        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    
    @Override
    public boolean isEnabled(){
        return true;
    }
    
   
  
}
