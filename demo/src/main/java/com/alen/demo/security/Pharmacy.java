package com.alen.demo.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.alen.demo.Address;
import com.alen.demo.Medicine;
import com.alen.demo.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PRIVATE,force=true)
@RequiredArgsConstructor
public class Pharmacy implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String username;

    @NotBlank
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private final String password;
    

    private final String phone;

    @NotNull
    private final String email;

    @OneToOne(
    
    cascade = CascadeType.ALL)
    private final Address address;

    @ManyToMany(
    
    cascade = CascadeType.ALL)
    private final List<Medicine> medicines;
    //new
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles=new HashSet<>();
   

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
