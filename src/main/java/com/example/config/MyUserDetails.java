package com.example.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.models.User;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class MyUserDetails implements UserDetails{

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        return Arrays.stream(user.getRoles().toString().split(", "))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    public Long getPrincipal(){
        return user.getId();
    }
    

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

}
